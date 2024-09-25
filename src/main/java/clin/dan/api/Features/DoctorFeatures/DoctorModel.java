package clin.dan.api.Features.DoctorFeatures;

import clin.dan.api.Address;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.InsertDoctorDTO;
import clin.dan.api.Features.DoctorFeatures.DoctorDTOs.UpdateDoctorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private String phone_number;

    private boolean active;


    public DoctorModel(InsertDoctorDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone_number = data.phoneNumber();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
        this.active = true;

    }

    public void updateDoctorInformation(UpdateDoctorDTO data) {
        if (data.name() != null){ this.name = data.name(); }
        if (data.phoneNumber() != null){ this.phone_number = data.phoneNumber(); }
        if (data.addressDataDTO() != null) {
            this.address.updateAddress(data.addressDataDTO());
        }

    }

    public void logicalDelete() {
        this.active = false;
    }
}
