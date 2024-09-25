package clin.dan.api.Features.Consultation.ConsultationDTOs;

import clin.dan.api.Features.Consultation.ConsultationModel;

import java.time.LocalDateTime;

public record ScheduledConsultationDetailDTO(
        String doctor_id,
        String patient_id,
        LocalDateTime date
) {
    public ScheduledConsultationDetailDTO(ConsultationModel consultationModel){
        this(consultationModel.getDoctor_id().getName(), consultationModel.getPatient_id().getName(), consultationModel.getDate());
    }
}
