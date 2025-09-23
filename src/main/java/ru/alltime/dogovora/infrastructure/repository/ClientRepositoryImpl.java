package ru.alltime.dogovora.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.domain.models.Client;
import ru.alltime.dogovora.domain.repository.ClientRepository;
import ru.alltime.dogovora.infrastructure.models.ClientEntity;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final JpaClientRepository jpa;

    @Override
    public Page<Client> findAll(String search, Pageable pageable) {
        Specification<ClientEntity> spec = (root, q, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.isTrue(root.get("isActive")); // только активные по умолчанию
            }
            String like = "%" + search.trim().toLowerCase() + "%";
            return cb.and(
                    cb.isTrue(root.get("isActive")),
                    cb.like(cb.lower(root.get("fullName")), like)
            );
        };
        Page<ClientEntity> page = jpa.findAll(spec, pageable);
        return page.map(ClientMapper::toDomain);
    }

    @Override
    public Optional<Client> findById(String id) {
        return jpa.findById(id).map(ClientMapper::toDomain);
    }

    @Override
    public Client save(Client client) {
        ClientEntity saved = jpa.save(ClientMapper.toEntity(client));
        return ClientMapper.toDomain(saved);
    }

    @Override
    public void softDelete(String id) {
        jpa.findById(id).ifPresent(e -> {
            e.setActive(false);
            jpa.save(e);
        });
    }
}
