package ru.alltime.dogovora.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alltime.dogovora.domain.models.User;
import ru.alltime.dogovora.domain.repository.UserRepository;
import ru.alltime.dogovora.infrastructure.models.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    // ====== QUERIES ======

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(String search, Pageable pageable) {
        String base = "SELECT u FROM UserEntity u WHERE u.active = true";
        String countBase = "SELECT COUNT(u) FROM UserEntity u WHERE u.active = true";

        boolean hasSearch = search != null && !search.isBlank();
        if (hasSearch) {
            String filter = " AND (LOWER(u.login) LIKE :q OR LOWER(u.firstName) LIKE :q OR LOWER(u.secondName) LIKE :q OR LOWER(u.thirdName) LIKE :q)";
            base += filter;
            countBase += filter;
        }

        // сортировка из Pageable
        String orderBy = buildOrderBy(pageable.getSort(), "u");
        if (!orderBy.isEmpty()) base += " " + orderBy;

        TypedQuery<UserEntity> query = em.createQuery(base, UserEntity.class);
        TypedQuery<Long> countQuery = em.createQuery(countBase, Long.class);

        if (hasSearch) {
            String like = "%" + search.toLowerCase().trim() + "%";
            query.setParameter("q", like);
            countQuery.setParameter("q", like);
        }

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<UserEntity> entities = query.getResultList();
        List<User> content = new ArrayList<>(entities.size());
        for (UserEntity e : entities) content.add(toDomain(e));

        long total = countQuery.getSingleResult();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(String id) {
        UserEntity e = em.find(UserEntity.class, id);
        return Optional.ofNullable(e).map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        // username == login (по твоей схеме)
        String jpql = "SELECT u FROM UserEntity u WHERE u.active = true AND u.login = :login";
        List<UserEntity> list = em.createQuery(jpql, UserEntity.class)
                .setParameter("login", username)
                .setMaxResults(1)
                .getResultList();
        return list.isEmpty() ? Optional.empty() : Optional.of(toDomain(list.get(0)));
    }

    // ====== SAVE / DELETE ======

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId().isBlank()) {
            user.setId(UUID.randomUUID().toString());
        }
        // если это создание и флаг активен не выставлен — сделаем активным
        if (!exists(user.getId())) {
            user.setActive(true);
        }

        UserEntity entity = toEntity(user);
        if (exists(entity.getId())) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        return toDomain(entity);
    }

    @Override
    public void softDelete(String id) {
        UserEntity e = em.find(UserEntity.class, id);
        if (e != null) {
            e.setActive(false);
            em.merge(e);
        }
    }

    // ====== HELPERS ======

    private boolean exists(String id) {
        return id != null && em.find(UserEntity.class, id) != null;
    }

    private String buildOrderBy(Sort sort, String alias) {
        if (sort == null || sort.isUnsorted()) return "";
        StringBuilder sb = new StringBuilder("ORDER BY ");
        boolean first = true;
        for (Sort.Order o : sort) {
            if (!first) sb.append(", ");
            first = false;
            String prop = switch (o.getProperty()) {
                case "login" -> alias + ".login";
                case "firstName" -> alias + ".firstName";
                case "secondName" -> alias + ".secondName";
                case "thirdName" -> alias + ".thirdName";
                case "role" -> alias + ".role";
                case "position" -> alias + ".position";
                case "active" -> alias + ".active";
                default -> alias + ".secondName";
            };
            sb.append(prop).append(" ").append(o.getDirection().isAscending() ? "ASC" : "DESC");
        }
        return sb.toString();
    }

    private User toDomain(UserEntity e) {
        if (e == null) return null;
        return User.builder()
                .id(e.getId())
                .active(e.isActive())
                .login(e.getLogin())
                .firstName(e.getFirstName())
                .secondName(e.getSecondName())
                .thirdName(e.getThirdName())
                .role(e.getRole())          // enum Roles
                .position(e.getPosition())
                .build();
    }

    private UserEntity toEntity(User d) {
        if (d == null) return null;
        return UserEntity.builder()
                .id(d.getId())
                .active(d.isActive())
                .login(d.getLogin())
                .firstName(d.getFirstName())
                .secondName(d.getSecondName())
                .thirdName(d.getThirdName())
                .role(d.getRole())          // enum Roles
                .position(d.getPosition())
                .build();
    }
}