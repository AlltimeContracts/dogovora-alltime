package ru.alltime.dogovora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        var userDTO = userService.findUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Первичное создание пользователя (регистрация)
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        var userCreateRequest = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateRequest);
    }

    //TODO этого метода в итоговой версии быть не должно!
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        //TODO возвращать не голую строку, а DTO
        return userService.verify(userDTO);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        var updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Delete user successfully");
    }
}
