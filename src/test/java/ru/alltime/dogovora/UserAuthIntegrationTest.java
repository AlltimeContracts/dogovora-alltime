package ru.alltime.dogovora;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.alltime.dogovora.dto.userDTOs.UserRegisterDTO;
import ru.alltime.dogovora.dto.userDTOs.UserResponseDTO;

import java.util.UUID;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.HttpStatus;
import ru.alltime.dogovora.dto.RefreshTokenRequestDTO;
import ru.alltime.dogovora.security.jwt.JWTService;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Поднимает весь контекст приложения
@AutoConfigureMockMvc // Автоматически настраивает MockMvc для отправки запросов
@Transactional // Откатывает изменения в базе данных после завершения теста
@RequiredArgsConstructor
class UserAuthIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final JWTService jwtService;

    private String userLogin;
    private UUID userId;
    private String accessToken;
    private String refreshToken;

    @BeforeEach
    void registerAndLogin() throws Exception {
        userLogin = "flow-test-" + UUID.randomUUID() + "@alltime.ru";
        String password = "SuperSecurePassword123";

        UserRegisterDTO registrationPayload = new UserRegisterDTO(
                userLogin,
                password,
                "Алексей",
                "Петров",
                null,
                "Разработчик"
        );

        // 1. Регистрация
        MvcResult registrationResult = mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registrationPayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(userLogin))
                .andReturn();

        String responseContent = registrationResult.getResponse().getContentAsString();
        UserResponseDTO registeredUser = objectMapper.readValue(responseContent, UserResponseDTO.class);
        userId = registeredUser.id();

        // 2. Логин
        UserRegisterDTO loginPayload = new UserRegisterDTO(userLogin, password, null,
                null, null, null);

        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginPayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(notNullValue()))
                .andExpect(jsonPath("$.refreshToken").value(notNullValue()))
                .andReturn();

        JsonNode tokens = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        accessToken = tokens.get("accessToken").asText();
        refreshToken = tokens.get("refreshToken").asText();
    }

    @Test
    @DisplayName("Сквозной сценарий: Регистрация -> Логин -> Получение данных по токену")
    void fullAuthFlowWorkflow() throws Exception {
        // 3. Запрос к защищенному ресурсу
        mockMvc.perform(get("/api/v1/users/{id}", userId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()))
                .andExpect(jsonPath("$.login").value(userLogin));
    }

    @Test
    @DisplayName("accessToken живёт 15 минут: просроченный токен блокирует доступ → " +
            "refreshToken выдаёт новый валидный accessToken")
    void expiredAccessTokenRequiresRefreshToken() throws Exception {
        // 4. Проверяем, что accessToken истекает ровно через 15 минут
        JsonNode accessClaims = decodeJwtPayload(accessToken);
        long iat = accessClaims.get("iat").asLong();
        long exp = accessClaims.get("exp").asLong();
        assertThat(exp - iat)
                .as("accessToken должен истекать через 15 минут")
                .isEqualTo(15 * 60L);

        // 5. Симулируем истечение: создаём правильно подписанный, но уже просроченный токен
        String expiredToken = buildExpiredAccessToken(userLogin);

        // 6. Просроченный accessToken не открывает доступ к защищённому ресурсу
        MvcResult deniedResult = mockMvc.perform(get("/api/v1/users/{id}", userId)
                        .header("Authorization", "Bearer " + expiredToken))
                .andReturn();
        assertThat(deniedResult.getResponse().getStatus())
                .as("Просроченный accessToken не должен давать доступ")
                .isNotEqualTo(HttpStatus.OK.value());

        // 7. /refresh-token принимает refreshToken и возвращает новый accessToken
        MvcResult refreshResult = mockMvc.perform(post("/api/v1/auth/refresh-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new RefreshTokenRequestDTO(refreshToken))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value(notNullValue()))
                .andReturn();

        String newAccessToken = objectMapper.readTree(refreshResult.getResponse().getContentAsString())
                .get("accessToken").asText();

        // 8. Новый accessToken открывает доступ к защищённому ресурсу
        mockMvc.perform(get("/api/v1/users/{id}", userId)
                        .header("Authorization", "Bearer " + newAccessToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId.toString()));
    }

    /**
     * Декодирует payload JWT без проверки подписи (base64url → JSON)
     */
    private JsonNode decodeJwtPayload(String token) throws Exception {
        String[] parts = token.split("\\.");
        byte[] decoded = Base64.getUrlDecoder().decode(parts[1]);
        return objectMapper.readTree(decoded);
    }

    /**
     * Строит корректно подписанный, но уже просроченный accessToken —
     * симулирует ситуацию, когда прошло более 15 минут с момента выдачи
     */
    private String buildExpiredAccessToken(String login) throws Exception {
        Field secretKeyField = JWTService.class.getDeclaredField("secretKey");
        secretKeyField.setAccessible(true);
        String rawKey = (String) secretKeyField.get(jwtService);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(rawKey));

        return Jwts.builder()
                .claims()
                .add(Map.of("type", "ACCESS"))
                .subject(login)
                .issuedAt(Date.from(Instant.now().minus(20, ChronoUnit.MINUTES)))
                .expiration(Date.from(Instant.now().minus(5, ChronoUnit.MINUTES)))
                .and()
                .signWith(key)
                .compact();
    }
}
