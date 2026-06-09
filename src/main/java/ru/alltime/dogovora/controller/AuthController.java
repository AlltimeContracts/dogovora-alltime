package ru.alltime.dogovora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alltime.dogovora.dto.JwtTokenDTO;
import ru.alltime.dogovora.dto.userDTOs.UserRegisterDTO;
import ru.alltime.dogovora.dto.userDTOs.UserResponseDTO;
import ru.alltime.dogovora.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    /**
     * Первичное создание пользователя (регистрация)
     */
    @PostMapping("/register")
    public UserResponseDTO createUser(@RequestBody UserRegisterDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public JwtTokenDTO login(@RequestBody UserRegisterDTO userDTO) {
        return new JwtTokenDTO(userService.verify(userDTO), null);
    }
}
