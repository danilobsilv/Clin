package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class OpeningHoursValidator implements ScheduleConsultationValidator{

    public void validate(ConsultationScheduleDataDTO data){
        var consultationDate = data.date();

        var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpeningTime = consultationDate.getHour() < 7;
        var afterWorkingTime = consultationDate.getHour() > 18;

        if (sunday || beforeOpeningTime || afterWorkingTime){
            throw new ValidationErrorException("Consultation outside opening hours.");
        }
    }
}
