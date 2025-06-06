package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dan_u_sedmici")
public class DanUSedmici {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "naziv", nullable = false, length = 45)
    private String naziv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}