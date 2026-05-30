package ru.alltime.dogovora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alltime.dogovora.dto.userDTOs.UserResponseDTO;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.security.AuthenticatedUserArgumentResolver.AuthenticatedUser;
import ru.alltime.dogovora.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public UserResponseDTO updateUser(@RequestBody UserResponseDTO userDTO,
                                      @AuthenticatedUser User authenticatedUser) {
        if (!userDTO.id().equals(authenticatedUser.getId())) {
            throw new ResponseStatusException(403, "Not allowed to update other's profile", null);
        }

        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id,
                               @AuthenticatedUser User authenticatedUser) {
        if (!id.equals(authenticatedUser.getId())) {
            throw new ResponseStatusException(403, "Not allowed to delete other's profile", null);
        }

        userService.deleteById(id);
    }
}
