package clin.dan.api.Features.DoctorFeatures.DoctorDTOs;

import clin.dan.api.AddressDataDTO;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        AddressDataDTO addressDataDTO) {
}
