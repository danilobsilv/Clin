package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ConsultationHourValidator implements ScheduleConsultationValidator{

    public void validate(ConsultationScheduleDataDTO data){
        var consultationDate = data.date();
        var now = LocalDateTime.now();
        var timeDeltaOneHour = Duration.between(now, consultationDate).toMinutes();

        if (timeDeltaOneHour < 30){
            throw new ValidationErrorException("Consultation must be scheduled 30 minutes in advance");
        }
    }
}
