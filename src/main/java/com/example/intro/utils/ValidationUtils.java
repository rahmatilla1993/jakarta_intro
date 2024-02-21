package com.example.intro.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtils {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static <T> Map<String, String> validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        Map<String, String> errorMessages = new HashMap<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errorMessages.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return errorMessages;
    }
}
