package clin.dan.api.Features.PatientFeatures;


import clin.dan.api.Address;
import clin.dan.api.Features.PatientFeatures.PatientDTO.PatientRegistrationDTO;
import clin.dan.api.Features.PatientFeatures.PatientDTO.UpdatePatienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Address getAddress() {
        return address;
    }

    public PatientModel(PatientRegistrationDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.address = new Address(dados.endereco());
    }

    public void atualizarInformacoes(UpdatePatienteDTO dados) {
        if (dados.nome() != null){ this.nome = dados.nome(); }
        if (dados.email() != null) { this.email = dados.email(); }
        if (dados.telefone() != null) {this.telefone = dados.telefone(); }
    }
}
