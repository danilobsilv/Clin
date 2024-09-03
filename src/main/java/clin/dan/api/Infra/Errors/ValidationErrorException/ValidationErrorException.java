package clin.dan.api.Infra.Errors.ValidationErrorException;

public class ValidationErrorException extends RuntimeException {
    public ValidationErrorException(String errorMessage) {
        super(errorMessage);
    }
}
