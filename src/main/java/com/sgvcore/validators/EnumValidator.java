package com.sgvcore.validators;

import com.sgvcore.validators.constraints.EnumValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<EnumValidation, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }
}

