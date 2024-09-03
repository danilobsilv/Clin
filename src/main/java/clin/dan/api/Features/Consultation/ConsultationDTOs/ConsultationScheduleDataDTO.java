package clin.dan.api.Features.Consultation.ConsultationDTOs;

import clin.dan.api.Features.DoctorFeatures.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultationScheduleDataDTO(
        Long doctorId,

        @NotNull
        Long patientId,

        @NotNull
        @Future
        LocalDateTime date,

        Specialty specialty
) {

}
