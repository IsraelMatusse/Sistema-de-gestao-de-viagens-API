package com.sgvcore.validators;

import com.sgvcore.validators.constraints.Nuit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class MatriculaValidator implements ConstraintValidator<Nuit, String> {
    private static final String MATRICULA_REGEX = "^[A-Z]{3}-\\d{3}-[A-Z]{2}$";
    private static final Pattern MATRICULA_PATTERN = Pattern.compile(MATRICULA_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return MATRICULA_PATTERN.matcher(value).matches();
    }
}
