package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.PatientFeatures.PatientRepository;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateActivePatient implements ScheduleConsultationValidator{

    @Autowired
    PatientRepository patientRepository;

    public void validate(ConsultationScheduleDataDTO data){
        var activePatient = patientRepository.findActiveById(data.patientId());

        if (!activePatient){
            throw new ValidationErrorException("This patient is not active.");
        }
    }

}
