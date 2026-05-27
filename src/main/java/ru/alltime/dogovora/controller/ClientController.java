package ru.alltime.dogovora.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.ClientDTO;
import ru.alltime.dogovora.service.ClientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients(){
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable UUID id) {
        ClientDTO client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody @Parameter(description = "передается DTO для создания клиента") ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = clientService.updateClient(clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
        var clientForDelete = clientService.findClientById(id);
        clientService.deleteClientById(id);
        return ResponseEntity.ok("Client with id: " + id + " successfully deleted.");

    }
}
