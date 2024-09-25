package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.CancelScheduledConsultationDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationDataDetailsDTO;
import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.Consultation.Validations.ScheduleConsultationValidator;
import clin.dan.api.Features.DoctorFeatures.DoctorModel;
import clin.dan.api.Features.DoctorFeatures.DoctorRepository;
import clin.dan.api.Features.PatientFeatures.PatientRepository;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleConsultation {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ScheduleConsultationValidator> validators;
    // searches for all classes that implements interface and inserts them on this list

    public ConsultationDataDetailsDTO scheduleConsultation(ConsultationScheduleDataDTO data) throws ValidationErrorException {
        if (!patientRepository.existsById(data.patientId())){
            throw new ValidationErrorException("Patient ID not found.");
        }

        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
            throw new ValidationErrorException("Doctor ID not found.");
        }

        validators.forEach(validator -> validator.validate(data));
 
        var doctor = randomlyChooseDoctor(data);
        if (doctor == null){
            throw new ValidationErrorException("There are no available doctor for the chosen date.");
        }

        var patient = patientRepository.getReferenceById(data.patientId());
        var consultation = new ConsultationModel(null, doctor, patient, data.date(), null);

        consultationRepository.save(consultation);

        return new ConsultationDataDetailsDTO(consultation);
    }

    private DoctorModel randomlyChooseDoctor(ConsultationScheduleDataDTO data){
        if (data.doctorId() != null){
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.specialty() == null){
            throw new ValidationErrorException("Specialty is a mandatory information when a doctor isn't chosen.");
        }
            return doctorRepository.chooseRandomFreeDoctorInTheDate(data.specialty(), data.date());
    }

    public void cancelScheduledConsultation(CancelScheduledConsultationDTO data){
        if (!consultationRepository.existsById(data.consultationId())){
            throw new ValidationErrorException("Could not find the ID (" + data.consultationId() + ") of this consultation.");
        };

        var consultation = consultationRepository.getReferenceById(data.consultationId());
        consultation.cancelConsultation (data.motivation());
    }
}












