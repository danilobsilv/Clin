package clin.dan.api.Features.DoctorFeatures.DoctorDTOs;

import clin.dan.api.Address;
import clin.dan.api.Features.DoctorFeatures.Specialty;
import clin.dan.api.Features.DoctorFeatures.DoctorModel;

public record DoctorDetailsDTO(
        Long id,
        String name,
        String email,
        String crm,
        String phoneNumber,
        Specialty specialty,
        Address address) {

    public DoctorDetailsDTO(DoctorModel doctorModel){
        this(doctorModel.getId(), doctorModel.getName(), doctorModel.getEmail(), doctorModel.getCrm(), doctorModel.getPhone_number(), doctorModel.getSpecialty(), doctorModel.getAddress());
    }

}
