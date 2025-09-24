package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Administrator;

import java.io.Serializable;
import java.util.Objects;

public class AdministratorDto implements Serializable {
    private final Long korisnikId;

    public AdministratorDto(Administrator administrator) {
        this.korisnikId = administrator.getKorisnik().getId();
    }

    public Long getKorisnikId() {return korisnikId;}

}
