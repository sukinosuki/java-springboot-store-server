package com.example.demo.util;

import com.example.demo.common.AppException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtil {

    public static void validateByGroup(Object o, Class<?> clazz) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Object>> validate = validator.validate(o, clazz);

        for (ConstraintViolation<Object> violation : validate) {
            throw AppException.paramError(violation.getMessage());
        }
    }
}
