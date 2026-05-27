package ru.alltime.dogovora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.alltime.dogovora.dto.JwtTokenDTO;
import ru.alltime.dogovora.dto.UserRegisterDTO;
import ru.alltime.dogovora.dto.UserResponseDTO;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.security.AuthenticatedUserArgumentResolver.AuthenticatedUser;
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
    public UserResponseDTO getUserById(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    /**
     * Первичное создание пользователя (регистрация)
     */
    @PostMapping("/register")
    public UserResponseDTO createUser(@RequestBody UserRegisterDTO userDTO) {
        return userService.createUser(userDTO);
    }

    //TODO этого метода в итоговой версии быть не должно!
    @PostMapping("/login")
    public JwtTokenDTO login(@RequestBody UserRegisterDTO userDTO) {
        return new JwtTokenDTO(userService.verify(userDTO), null);
    }

    @PutMapping
    public UserResponseDTO updateUser(@RequestBody UserResponseDTO userDTO,
                                              @AuthenticatedUser User user) {
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
