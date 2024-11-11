package com.jcmbeng.fundtransfer.validators;

import com.jcmbeng.fundtransfer.exceptions.InvalidEntityException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.BAD_REQUEST_DATA;

public class DtoValidator {
    public static Validator validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }

    /**
     * Validates the given DTO object and returns a list of validation error messages.
     *
     * @param dto The DTO object to validate.
     * @return A list of validation error messages, or an empty list if there are no validation errors.
     */
    public static List<String> validate(Object dto) {
        // List to hold validation error messages
        List<String> errors = new ArrayList<> ();

        // Create a ValidatorFactory and Validator instance
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Validate the DTO object and get the set of constraint violations
        Set<ConstraintViolation<Object>> violations = validator.validate(dto);

        // If there are constraint violations, add their messages to the errors list
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Object> violation : violations) {
                errors.add(violation.getMessage());
            }
            throw new InvalidEntityException (BAD_REQUEST_DATA, errors);
        }

        // Return the list of validation error messages
        return errors;
    }
}
