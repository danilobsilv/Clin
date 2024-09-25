package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;

public interface ScheduleConsultationValidator {

    void validate(ConsultationScheduleDataDTO data);

}
