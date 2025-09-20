package ru.alltime.dogovoraalltime.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alltime.dogovoraalltime.domain.models.Roles;
import ru.alltime.dogovoraalltime.domain.models.User;
import ru.alltime.dogovoraalltime.domain.repository.UserRepository;
import ru.alltime.dogovoraalltime.infrastructure.models.UserEntity;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(int id) {
        // В БД id хранится как строка, поэтому преобразуем
        String key = String.valueOf(id);
        UserEntity entity = entityManager.find(UserEntity.class, key);
        return mapToDomain(entity);
    }

    @Override
    public User findByUsername(String username) {
        try {
            TypedQuery<UserEntity> q = entityManager.createQuery(
                    "SELECT u FROM UserEntity u WHERE u.login = :login", UserEntity.class);
            q.setParameter("login", username);
            UserEntity entity = q.getSingleResult();
            return mapToDomain(entity);
        } catch (NoResultException ex) {
            return null;
        }
    }

    // ----- Маппер инфраструктурной сущности в доменную модель -----
    private User mapToDomain(UserEntity e) {
        if (e == null) return null;

        return new User(
                e.getId(),          // String id
                e.isActive(),       // boolean isActive
                e.getFirstName(),   // String firstName
                e.getSecondName(),  // String secondName
                e.getThirdName(),   // String thirdName (nullable)
                Roles.valueOf(e.getRole().toUpperCase()),        // String role
                e.getPosition()     // String position (nullable)
        );
    }
}