package clin.dan.api;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;

    private String neighborhood;

    private String cep;

    private String number;

    private String complement;

    private String city;

    private String uf;

    public Address(AddressDataDTO address) {
        this.street = address.city();
        this.neighborhood = address.neighbourhood();
        this.cep = address.cep();
        this.uf = address.uf();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }


    public void updateAddress(AddressDataDTO address) {
        if (address.street() != null) { this.street = address.street();}
        if (address.neighbourhood() != null) { this.neighborhood = address.neighbourhood(); }
        if (address.cep() != null) { this.cep = address.cep(); }
        if (address.uf() != null) {this.uf = address.uf(); }
        if (address.city() != null) {this.city = address.city(); }
        if (address.number() != null) { this.number = address.number(); }
        if (address.complement() != null) { this.complement = address.complement(); }
    }
}
