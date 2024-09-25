package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.Consultation.ConsultationRepository;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class doctorWithScheduledConsultationInTheSameTime implements ScheduleConsultationValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(ConsultationScheduleDataDTO data){
        var doctorWithScheduledConsultation = consultationRepository.existsByDoctorIdAndDate(data.doctorId(), data.date());

        if (!doctorWithScheduledConsultation){
            throw new ValidationErrorException("This doctor already has a scheduled consultation for this date.");
        }
    }
}
