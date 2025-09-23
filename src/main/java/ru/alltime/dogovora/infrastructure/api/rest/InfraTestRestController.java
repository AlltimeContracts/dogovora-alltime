package ru.alltime.dogovora.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import ru.alltime.dogovora.domain.models.User;
import ru.alltime.dogovora.domain.repository.UserRepository;

@RestController
@RequestMapping("/infra/test")
@RequiredArgsConstructor
public class InfraTestRestController {

    private final UserRepository userRepository;

    // Было: getUserById(int id) -> такого метода нет
    // Стало: findById(String id) -> Optional<User>
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // Было: Optional<User> присваивался в User
    // Стало: корректная распаковка Optional
    @GetMapping("/users/by-login")
    public User getUserByLogin(@RequestParam("login") String login) {
        return userRepository.findByUsername(login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // Простой ping для проверки
    @GetMapping("/ping")
    public String ping() {
        return "infra ok";
    }
}