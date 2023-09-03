package com.sgvcore.validators.constraints;

import com.sgvcore.validators.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface DateValidation {
    String message() default "Data invalida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
