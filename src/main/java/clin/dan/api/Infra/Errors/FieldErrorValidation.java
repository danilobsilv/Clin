package clin.dan.api.Infra.Errors;

import org.springframework.validation.FieldError;

public record FieldErrorValidation(String field, String message) {

    public FieldErrorValidation(FieldError error){
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
