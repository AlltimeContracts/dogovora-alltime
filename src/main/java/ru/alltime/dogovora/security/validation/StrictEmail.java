package ru.alltime.dogovora.security.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrictEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrictEmail {

    String message() default "Неверный формат email адреса (ожидается xxx@yyy.zzz)";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
