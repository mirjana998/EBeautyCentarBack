package com.example.ebeautycentar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`ocjena_pružene_usluge`")
public class OcjenaPruženeUsluge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registrovani_klijent_id", nullable = false)
    private RegistrovaniKlijent registrovaniKlijent;

    @Column(name = "ocjena", nullable = false)
    private Integer ocjena;

    @Column(name = "komentar", length = 500)
    private String komentar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rezervacija_id")
    private Rezervacija rezervacija;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

}