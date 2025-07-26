package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ocjena_klijenta")
public class OcjenaKlijenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vlasnik_salona_id", nullable = false)
    private VlasnikSalona vlasnikSalona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registrovani_klijent_id", nullable = false)
    private RegistrovaniKlijent registrovaniKlijent;

    @Column(name = "ocjena", nullable = false)
    private Integer ocjena;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VlasnikSalona getVlasnikSalona() {
        return vlasnikSalona;
    }

    public void setVlasnikSalona(VlasnikSalona vlasnikSalona) {
        this.vlasnikSalona = vlasnikSalona;
    }

    public RegistrovaniKlijent getRegistrovaniKlijent() {
        return registrovaniKlijent;
    }

    public void setRegistrovaniKlijent(RegistrovaniKlijent registrovaniKlijent) {
        this.registrovaniKlijent = registrovaniKlijent;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

}