package clin.dan.api;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressDataDTO endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
    }


    public void atualizarEndereco(AddressDataDTO endereco) {
        if (endereco.logradouro() != null) { this.logradouro = endereco.logradouro();}
        if (endereco.bairro() != null) { this.bairro = endereco.bairro(); }
        if (endereco.cep() != null) { this.cep = endereco.cep(); }
        if (endereco.uf() != null) {this.uf = endereco.uf(); }
        if (endereco.cidade() != null) {this.cidade = endereco.cidade(); }
        if (endereco.numero() != null) { this.numero = endereco.numero(); }
        if (endereco.complemento() != null) { this.complemento = endereco.complemento(); }
    }
}
