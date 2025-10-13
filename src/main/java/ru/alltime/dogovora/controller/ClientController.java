package ru.alltime.dogovora.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.service.ClientServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clients")
@AllArgsConstructor
public class ClientController {

    private ClientServiceImpl clientService;


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> allClients =  clientService.findAllClients();
        return ResponseEntity.ok(allClients);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id){
        Client client = clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping("/create-client")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/update-clint-info")
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client updatedClient = clientService.updateClient(client);
        return ResponseEntity.ok(updatedClient);
    }

}
