package ru.alltime.dogovora.security;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticatedUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        var parameterType = parameter.getParameterType();

        if (!User.class.equals(parameterType)) {
            throw new IllegalArgumentException("Token auth could not resolve parameter " + parameter);
        }

        var authenticatedUser = getAuthenticatedUser();
        if (authenticatedUser != null) {
            return authenticatedUser;
        }

        var annotation = parameter.getParameterAnnotation(AuthenticatedUser.class);
        if (annotation == null) {
            throw new IllegalArgumentException("Parameter has no @AuthenticatedUser annotation. Parameter: " + parameter);
        }

        var requiredParameter = annotation.required();
        if (requiredParameter) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authenticated");
        }

        return null;
    }

    private User getAuthenticatedUser() {
        var username = getAuthenticatedUsername();
        if (username == null) {
            return null;
        }
        return userRepository.findUserByLogin(username).orElse(null);
    }

    @Nullable
    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return null;
        }

        return authentication.getName();
    }

    /**
     * Аннотация для аргумента в методах RestController.
     * Аргумент должен быть типа User.
     * Внедряет текущего аутентифицированного пользователя.
     * Всегда обращается к базе данных и получает последние данные пользователя.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface AuthenticatedUser {
        boolean required() default true;
    }
}
