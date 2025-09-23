package ru.alltime.dogovora.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.domain.models.Contract;
import ru.alltime.dogovora.domain.repository.ContractRepository;
import ru.alltime.dogovora.infrastructure.dto.ContractDto;
import ru.alltime.dogovora.infrastructure.dto.ContractDtoMapper;

import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractRepository repo;

    @GetMapping
    public Page<ContractDto> list(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "contractNum,desc") String sort
    ) {
        String[] s = sort.split(",", 2);
        Sort.Direction dir = s.length > 1 ? Sort.Direction.fromString(s[1]) : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, s[0]));
        return repo.findAll(q, pageable).map(ContractDtoMapper::toDto);
    }

    @GetMapping("/{id}")
    public ContractDto get(@PathVariable String id) {
        Contract c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Contract not found"));
        return ContractDtoMapper.toDto(c);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContractDto create(@RequestBody ContractDto dto) {
        Contract toSave = ContractDtoMapper.toDomain(dto);
        if (toSave.getId() == null || toSave.getId().isBlank()) {
            toSave.setId(UUID.randomUUID().toString());
        }
        return ContractDtoMapper.toDto(repo.save(toSave));
    }

    @PutMapping("/{id}")
    public ContractDto update(@PathVariable String id, @RequestBody ContractDto dto) {
        Contract incoming = ContractDtoMapper.toDomain(dto);
        incoming.setId(id);
        return ContractDtoMapper.toDto(repo.save(incoming));
    }

    // Мягкое удаление: active=false
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repo.softDelete(id);
    }
}
