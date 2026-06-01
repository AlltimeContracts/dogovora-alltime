package ru.alltime.dogovora.security.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrictEmailValidator implements ConstraintValidator<StrictEmail, String> {

    // Поле должно быть валидным email-адресом формата xxx@yyy.zzz
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return true;
        }

        return email.matches(EMAIL_REGEX);
    }
}
