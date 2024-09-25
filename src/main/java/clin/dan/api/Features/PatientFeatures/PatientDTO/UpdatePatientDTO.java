package clin.dan.api.Features.PatientFeatures.PatientDTO;


import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
