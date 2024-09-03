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
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean ativo;


    public DoctorModel(InsertDoctorDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.specialty = dados.specialty();
        this.address = new Address(dados.endereco());
        this.ativo = true;

    }

    public void atualizarInformacoes(UpdateDoctorDTO dados) {
        if (dados.nome() != null){ this.nome = dados.nome(); }
        if (dados.telefone() != null){ this.telefone = dados.telefone(); }
        if (dados.addressDataDTO() != null) {
            this.address.atualizarEndereco(dados.addressDataDTO());
        }

    }

    public void exclusaoLogica() {
        this.ativo = false;
    }
}
