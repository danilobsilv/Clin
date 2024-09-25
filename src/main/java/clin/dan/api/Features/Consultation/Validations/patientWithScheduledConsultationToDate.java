package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.Consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class patientWithScheduledConsultationToDate implements ScheduleConsultationValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(ConsultationScheduleDataDTO data){
        var firstScheduledTime = data.date().withHour(7);
        var lastScheduledTime = data.date().withHour(18);
        var patientWithScheduledConsultation = consultationRepository.existsByPatientAndDateBetween(data.patientId());
    }
}
