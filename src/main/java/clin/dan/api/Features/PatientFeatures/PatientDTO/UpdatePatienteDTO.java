package clin.dan.api.Features.PatientFeatures.PatientDTO;


import jakarta.validation.constraints.NotNull;

public record UpdatePatienteDTO(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone
) {
}
