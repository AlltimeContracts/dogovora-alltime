package ru.alltime.dogovoraalltime.infrastructure.api.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alltime.dogovoraalltime.domain.models.User;
import ru.alltime.dogovoraalltime.domain.repository.UserRepository;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/infra/api")
public class InfraTestRestController {

    private final UserRepository userRepository;

    public InfraTestRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "OK");
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("pong", OffsetDateTime.now().toString());
    }

    // Пробное чтение пользователя по id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable int id) {
        User u = userRepository.getUserById(id);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    // Пробное чтение по логину
    @GetMapping("/users/by-login/{login}")
    public ResponseEntity<User> getByLogin(@PathVariable String login) {
        User u = userRepository.findByUsername(login);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }
}