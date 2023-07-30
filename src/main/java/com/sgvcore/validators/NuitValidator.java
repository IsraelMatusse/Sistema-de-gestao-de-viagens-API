package com.sgvcore.validators;

import com.sgvcore.validators.constraints.Nuit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NuitValidator implements ConstraintValidator<Nuit, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 9) {
            return false;
        }

        char firstChar = value.charAt(0);
        char secondChar = value.charAt(1);

        // Verifica se o NUIT começa com 1 para individuais ou 2 para empresas
        if (firstChar != '1' && firstChar != '2') {
            return false;
        }

        // Verifica se todos os caracteres são dígitos numéricos
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}