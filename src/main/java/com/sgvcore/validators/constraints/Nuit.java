package com.sgvcore.validators.constraints;

import com.sgvcore.validators.NuitValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NuitValidator.class)
public @interface Nuit {
    String message() default "Número NUIT inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
