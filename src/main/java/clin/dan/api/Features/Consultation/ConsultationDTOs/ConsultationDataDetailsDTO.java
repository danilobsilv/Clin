package clin.dan.api.Features.Consultation.ConsultationDTOs;

import clin.dan.api.Features.Consultation.ConsultationModel;

import java.time.LocalDateTime;

public record ConsultationDataDetailsDTO(
    Long id,
    Long doctorId,
    Long patientId,
    LocalDateTime date
) {
    public ConsultationDataDetailsDTO(ConsultationModel consultation) {
        this(consultation.getId(), consultation.getDoctor_id().getId(), consultation.getPatient_id().getId(), consultation.getDate());
    }
}
