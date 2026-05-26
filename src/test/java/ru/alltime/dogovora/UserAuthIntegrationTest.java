package ru.alltime.dogovora;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.alltime.dogovora.dto.UserDTO;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest // Поднимает весь контекст приложения
@AutoConfigureMockMvc // Автоматически настраивает MockMvc для отправки запросов
@Transactional // Откатывает изменения в базе данных после завершения теста
@RequiredArgsConstructor
class UserAuthIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    @DisplayName("Сквозной сценарий: Регистрация -> Логин -> Получение данных по токену")
    void fullAuthFlowWorkflow() throws Exception {
        String uniqueLogin = "flow-test-" + UUID.randomUUID() + "@alltime.ru";
        String password = "SuperSecurePassword123";

        UserDTO registrationPayload = new UserDTO(
                null,
                "Алексей",
                "Петров",
                null,
                "Разработчик",
                uniqueLogin,
                password,
                true
        );

        // 1. Регистрация
        MvcResult registrationResult = mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationPayload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value(uniqueLogin))
                .andReturn();

        String responseContent = registrationResult.getResponse().getContentAsString();
        UserDTO registeredUser = objectMapper.readValue(responseContent, UserDTO.class);
        UUID createdUserId = registeredUser.id();

        // 2. Логин
        UserDTO loginPayload = new UserDTO(null, null, null, null, null,
                uniqueLogin, password, true);

        MvcResult loginResult = mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginPayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(notNullValue()))
                .andReturn();

        String loginResponse = loginResult.getResponse().getContentAsString();
        String jwtToken = objectMapper.readTree(loginResponse).get("accessToken").asText();

        // 3. Запрос к защищенному ресурсу
        mockMvc.perform(get("/api/v1/users/{id}", createdUserId)
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdUserId.toString()))
                .andExpect(jsonPath("$.login").value(uniqueLogin));
    }
}
