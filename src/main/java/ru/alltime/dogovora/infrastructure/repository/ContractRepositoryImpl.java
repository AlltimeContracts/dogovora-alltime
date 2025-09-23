package ru.alltime.dogovora.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.domain.models.Contract;
import ru.alltime.dogovora.domain.repository.ContractRepository;
import ru.alltime.dogovora.infrastructure.models.ContractEntity;
import ru.alltime.dogovora.infrastructure.models.HistoryRecEntity;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContractRepositoryImpl implements ContractRepository {

    private final JpaContractRepository jpa;
    private final JpaHistoryRepository historyJpa;

    @Override
    public Page<Contract> findAll(String search, Pageable pageable) {
        Specification<ContractEntity> spec = (root, q, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.isTrue(root.get("active"));
            }
            String like = "%" + search.trim().toLowerCase() + "%";
            return cb.and(
                    cb.isTrue(root.get("active")),
                    cb.like(cb.lower(root.get("contractNum")), like)
            );
        };
        Page<ContractEntity> page = jpa.findAll(spec, pageable);
        // Историю на листе не подтягиваем ради производительности
        return page.map(e -> ContractMapper.toDomain(e, null));
    }

    @Override
    public Optional<Contract> findById(String id) {
        return jpa.findById(id).map(e -> {
            List<HistoryRecEntity> hist = historyJpa.findByContractIdOrderByIdAsc(e.getId());
            return ContractMapper.toDomain(e, hist);
        });
    }

    @Override
    public Contract save(Contract contract) {
        ContractEntity saved = jpa.save(ContractMapper.toEntity(contract));
        // Историю (если пришла) перезапишем (append): просто добавим новые записи
        if (contract.getHistoryList() != null) {
            for (var hr : contract.getHistoryList()) {
                historyJpa.save(ContractMapper.histToEntity(saved.getId(), hr));
            }
        }
        List<HistoryRecEntity> hist = historyJpa.findByContractIdOrderByIdAsc(saved.getId());
        return ContractMapper.toDomain(saved, hist);
    }

    @Override
    public void softDelete(String id) {
        jpa.findById(id).ifPresent(e -> {
            e.setActive(false);
            jpa.save(e);
        });
    }
}
