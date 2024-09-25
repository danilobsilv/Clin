package clin.dan.api.Features.PatientFeatures.PatientDTO;

import clin.dan.api.Features.PatientFeatures.PatientModel;

public record PatientListingDataDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String cpf
) {

    public PatientListingDataDTO(PatientModel patientModel){
        this(patientModel.getId(), patientModel.getName(), patientModel.getEmail(), patientModel.getPhone_number(), patientModel.getCpf());
    }

}
