package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Salon;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SalonPutDto {

    private Long id;
    private String naziv;
    private String email;
    private String brojTelefona;

    // JSON Creator konstruktor za deserializaciju sa frontenda
    @JsonCreator
    public SalonPutDto(
            @JsonProperty("id") Long id,
            @JsonProperty("naziv") String naziv,
            @JsonProperty("email") String email,
            @JsonProperty("brojTelefona") String brojTelefona
    ) {
        this.id = id;
        this.naziv = naziv;
        this.email = email;
        this.brojTelefona = brojTelefona;
    }

    public SalonPutDto(Salon salon) {
        this.id = salon.getId();
        this.naziv = salon.getNaziv();
        this.email = salon.getEmail();
        this.brojTelefona = salon.getBrojTelefona();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBrojTelefona() { return brojTelefona; }
    public void setBrojTelefona(String brojTelefona) { this.brojTelefona = brojTelefona; }

}
