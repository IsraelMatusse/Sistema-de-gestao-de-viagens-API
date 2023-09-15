package com.sgvcore.validators;

import com.sgvcore.validators.constraints.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateValidator implements ConstraintValidator<DateValidation, Date> {

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        return !value.before(new Date());
    }
}
