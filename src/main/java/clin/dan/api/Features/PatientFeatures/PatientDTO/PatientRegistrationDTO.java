package clin.dan.api.Features.PatientFeatures.PatientDTO;

import clin.dan.api.AddressDataDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientRegistrationDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String telefone,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String cpf,

        @NotNull
        @Valid
        AddressDataDTO endereco
) {
}
