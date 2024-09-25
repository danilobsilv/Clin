package clin.dan.api.Features.PatientFeatures;


import clin.dan.api.Address;
import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientRegistrationDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.UpdatePatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "pacientes")
@Entity(name = "Patients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone_number;

    private String cpf;

    @Embedded
    private Address address;

    public PatientModel(PatientRegistrationDTO data) {
        this.name = data.nome();
        this.email = data.email();
        this.phone_number = data.phoneNumber();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updatePatientInformation(UpdatePatientDTO data) {
        if (data.name() != null){ this.name = data.name(); }
        if (data.email() != null) { this.email = data.email(); }
        if (data.phoneNumber() != null) {this.phone_number = data.phoneNumber(); }
    }
}
