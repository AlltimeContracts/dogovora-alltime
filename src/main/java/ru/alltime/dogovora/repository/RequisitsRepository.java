package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.Requisits;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisitsRepository extends JpaRepository<Requisits, Long> {

    Optional<Requisits> findByOgrnOgrnip(String ogrnOgrnip); // Поиск по ОГРН/ОГРНИП

    Optional<Requisits> findByInn(String inn); // Поиск по ИНН

    Optional<Requisits> findByKpp(String kpp); // Поиск по КПП

    List<Requisits> findByLegalAddressContainingIgnoreCase(String partOfAddress); // Поиск по юридическому адресу

    List<Requisits> findByActualAddressContainingIgnoreCase(String partOfAddress); // Поиск по фактическому адресу
}
