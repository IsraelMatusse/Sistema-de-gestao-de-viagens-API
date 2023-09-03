package com.sgvcore.validators.constraints;

import com.sgvcore.validators.MatriculaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MatriculaValidator.class)
public @interface Matricula {
    String message() default "Matrícula inválida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
