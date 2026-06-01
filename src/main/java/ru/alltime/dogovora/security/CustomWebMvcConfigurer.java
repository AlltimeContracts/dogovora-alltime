package ru.alltime.dogovora.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Глобальная конфигурация Spring MVC веб-слоя
 */
@Configuration
@RequiredArgsConstructor
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    private final AuthenticatedUserArgumentResolver resolver;

    /**
     * Автоматически добавляет префикс "/api/v1" ко всем @RestController
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/v1", HandlerTypePredicate.forAnnotation(RestController.class));
    }

    /**
     * Регистрирует кастомный обработчик аргументов для контроллеров
     * Source: https://reflectoring.io/spring-boot-argumentresolver/
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers
    ) {
        resolvers.addFirst(resolver);
    }
}
