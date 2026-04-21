package ru.alltime.dogovora.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.ClientRequestDTO;
import ru.alltime.dogovora.dto.ClientResponseDTO;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.service.ClientService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> allClients = clientService.findAllClients();
        return ResponseEntity.ok(allClients);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID id) {
        ClientResponseDTO client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    /**
        TODO после рефакторинга мапперов сделать обработку businessForm кириллическими названиями,
        а не перечислениями из BusinessForm
     */
    @PostMapping("/create-client")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Parameter(description = "передается DTO для создания клиента") ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO createdClient = clientService.createClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/update-clint-info")
    public ResponseEntity<ClientResponseDTO> updateClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO updatedClient = clientService.updateClient(clientRequestDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
        var clientForDelete = clientService.findClientById(id);
        clientService.deleteClientById(id);
        return ResponseEntity.ok("Client with id: " + id + " successfully deleted.");

    }
}
