package ru.alltime.dogovora.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.domain.models.Client;
import ru.alltime.dogovora.domain.repository.ClientRepository;
import ru.alltime.dogovora.infrastructure.dto.ClientDto;
import ru.alltime.dogovora.infrastructure.dto.ClientDtoMapper;

import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository repo;

    @GetMapping
    public Page<ClientDto> list(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "fullName,asc") String sort
    ) {
        String[] s = sort.split(",", 2);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(s.length > 1 ? s[1] : "asc"), s[0]));
        return repo.findAll(q, pageable).map(ClientDtoMapper::toDto);
    }

    @GetMapping("/{id}")
    public ClientDto get(@PathVariable String id) {
        Client c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return ClientDtoMapper.toDto(c);
    }

    @PostMapping
    public ClientDto create(@RequestBody ClientDto dto) {
        Client toSave = ClientDtoMapper.toDomain(dto);
        if (toSave.getId() == null || toSave.getId().isBlank()) {
            toSave.setId(UUID.randomUUID().toString());
        }
        return ClientDtoMapper.toDto(repo.save(toSave));
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable String id, @RequestBody ClientDto dto) {
        Client incoming = ClientDtoMapper.toDomain(dto);
        incoming.setId(id);
        return ClientDtoMapper.toDto(repo.save(incoming));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repo.softDelete(id);
    }
}
