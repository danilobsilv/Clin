package clin.dan.api.Features.Consultation;

import clin.dan.api.Features.Consultation.ConsultationDTOs.ConsultationScheduleDataDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorModel;
import clin.dan.api.Features.DoctorFeatures.DoctorRepository;
import clin.dan.api.Features.PatientFeatures.PatientRepository;
import clin.dan.api.Infra.Errors.ValidationErrorException.ValidationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultationRules {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void scheduleConsultation(ConsultationScheduleDataDTO data) throws ValidationErrorException {
        if (!patientRepository.existsById(data.patientId())){
            throw new ValidationErrorException("Patient ID not found.");
        }

        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
            throw new ValidationErrorException("Doctor ID not found.");
        }
        var doctor = randomlyChooseDoctor(data);
        var patient = patientRepository.getReferenceById(data.patientId());
        var consultation = new ConsultationModel(null, doctor, patient, data.date());

        consultationRepository.save(consultation);
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
}












