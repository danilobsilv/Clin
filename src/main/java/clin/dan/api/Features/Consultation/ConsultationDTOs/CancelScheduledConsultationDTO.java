package clin.dan.api.Features.Consultation.ConsultationDTOs;

import clin.dan.api.Features.Consultation.CancelMotivation;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record CancelScheduledConsultationDTO(
        @NotNull
        Long consultationId,

        @NotNull
        @Enumerated
        CancelMotivation motivation

) {
}
