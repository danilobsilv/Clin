package clin.dan.api.Features.PatientFeatures.PatientDTO;

import clin.dan.api.Features.PatientFeatures.PatientModel;

public record PatientListingDataDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf
) {

    public PatientListingDataDTO(PatientModel patientModel){
        this(patientModel.getId(), patientModel.getNome(), patientModel.getEmail(), patientModel.getTelefone(), patientModel.getCpf());
    }

}
