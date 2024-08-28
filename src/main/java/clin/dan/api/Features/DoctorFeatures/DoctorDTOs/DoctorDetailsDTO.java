package clin.dan.api.Features.DoctorFeatures.DoctorDTOs;

import clin.dan.api.Address;
import clin.dan.api.Features.DoctorFeatures.Specialty;
import clin.dan.api.Features.DoctorFeatures.DoctorModel;

public record DoctorDetailsDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Specialty specialty,
        Address address) {

    public DoctorDetailsDTO(DoctorModel doctorModel){
        this(doctorModel.getId(), doctorModel.getNome(), doctorModel.getEmail(), doctorModel.getCrm(), doctorModel.getTelefone(), doctorModel.getSpecialty(), doctorModel.getAddress());
    }

}
