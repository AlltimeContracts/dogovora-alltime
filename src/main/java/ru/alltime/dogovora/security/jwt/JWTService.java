package ru.alltime.dogovora.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private enum TokenType { ACCESS, REFRESH }

    //TODO хранить где-то на диске, а не в оперативной памяти, на случай падения сервиса
    private final String secretKey;
    private static final int ACCESS_TOKEN_EXPIRATION_MINUTES = 15;
    private static final int REFRESH_TOKEN_EXPIRATION_DAYS = 30;
    private static final String TOKEN_TYPE_CLAIM = "type";

    public JWTService() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            var sk  = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateAccessToken(String login) {
        return buildToken(login, TokenType.ACCESS, ACCESS_TOKEN_EXPIRATION_MINUTES, ChronoUnit.MINUTES);
    }

    public String generateRefreshToken(String login) {
        return buildToken(login, TokenType.REFRESH, REFRESH_TOKEN_EXPIRATION_DAYS, ChronoUnit.DAYS);
    }

    /**
     * Генерация различных видов токенов
     * @param login Логин пользователя
     * @param type Тип обновляемого токена (access, refresh)
     * @param amount Кол-во у.е. срока жизни токена
     * @param unit Единица измерения срока жизни токена
     * @return сгенерированный токен
     */
    private String buildToken(String login, TokenType type, long amount, ChronoUnit unit) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(TOKEN_TYPE_CLAIM, type.name());

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(login)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(Date.from(Instant.now().plus(amount, unit)))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String extractLogin(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Шаблонная функция получения утверждений из токена
     * @param token Токен
     * @param claimResolver Функция для извлечения утверждения из токена
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String login = extractLogin(token);
        String type = extractClaim(token, claims -> claims.get(TOKEN_TYPE_CLAIM, String.class));
        return login.equals(userDetails.getUsername()) && !isTokenExpired(token) && TokenType.ACCESS.name().equals(type);
    }

    public boolean validateRefreshToken(String token) {
        try {
            String type = extractClaim(token, claims -> claims.get(TOKEN_TYPE_CLAIM, String.class));
            return TokenType.REFRESH.name().equals(type);
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
