package clin.dan.api.Features.Consultation.Validations;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorRepository;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class isActiveDoctorValidator implements ScheduleConsultationValidator{

    @Autowired
    DoctorRepository doctorRepository;

    public void validate(ConsultationScheduleDataDTO data){
        if (data.doctorId() == null){
            return;
        }

        var isActiveDoctor = doctorRepository.findActiveDoctorById(data.doctorId());

        if (!isActiveDoctor){
            throw new ValidationErrorException("This doctor is not active anymore.");
        }
    }

}
