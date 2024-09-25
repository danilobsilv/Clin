package clin.dan.api.Features.DoctorFeatures.DoctorDTOs;


/*
 * bring onlY:
 * - id
 * - name
 * - email
 * - crm
 * - specialty
  */



import clin.dan.api.Features.DoctorFeatures.Specialty;
import clin.dan.api.Features.DoctorFeatures.DoctorModel;

public record DoctorListingDataDTO(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {


    public DoctorListingDataDTO(DoctorModel doctorModel){
        this(doctorModel.getId(), doctorModel.getName(), doctorModel.getEmail(), doctorModel.getCrm(), doctorModel.getSpecialty());
    }
}
