package clin.dan.api.Features.Consultation.ConsultationDTOs;

import java.time.LocalDateTime;

public record ConsultationDataDetailsDTO(
    Long id,
    Long doctorId,
    Long patientId,
    LocalDateTime date
) {
}
