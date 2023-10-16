package com.example.demo.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyFieldsValidator implements ConstraintValidator<NotEmptyFields, List<Long>> {

    @Override
    public void initialize(NotEmptyFields notEmptyFields) {
    }

    @Override
    public boolean isValid(List<Long> objects, ConstraintValidatorContext context) {
//        return objects.stream().allMatch(nef -> nef != null && !nef.trim().isEmpty());
//        return objects.stream().allMatch(nef -> nef != null);
        return !objects.isEmpty();
    }

}
