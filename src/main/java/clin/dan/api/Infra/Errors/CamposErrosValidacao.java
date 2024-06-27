package clin.dan.api.Infra.Errors;

import org.springframework.validation.FieldError;

public record CamposErrosValidacao(String campo, String mensagem) {

    public CamposErrosValidacao(FieldError error){
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
