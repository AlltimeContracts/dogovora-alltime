package ru.alltime.dogovora.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor

public class UserController {

    private UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{login}")
    public ResponseEntity<UserDTO> getUserByLogin(@PathVariable String login) {
        var userDTO = userService.findUserByLogin(login);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/by-name/{firstName}")
    public ResponseEntity<List<UserDTO>> getUserByFirstName(@PathVariable String firstName) {
        var users = userService.findUsersByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        var userCreateRequest = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateRequest);
    }

    @PutMapping("/update-user-info")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        var updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    }

    @DeleteMapping("delete-user/{login}")
    public ResponseEntity<String> deleteUserByLogin(@PathVariable String login) {
        var user = userService.findUserByLogin(login);
        userService.deleteByLogin(login);
        return ResponseEntity.ok("Delete user successfully");
    }
}
