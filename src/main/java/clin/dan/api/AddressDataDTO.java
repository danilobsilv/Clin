package clin.dan.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDataDTO(
        @NotBlank
        String street,
        @NotBlank
        String neighbourhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String city,
        @NotBlank
        String uf,
        String number,
        String complement) {
}
