package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OcjenaKlijentaId implements Serializable {
    private static final long serialVersionUID = 4035007034512787908L;
    @Column(name = "vlasnik_salona_id", nullable = false)
    private Long vlasnikSalonaId;

    @Column(name = "registrovani_klijent_id", nullable = false)
    private Long registrovaniKlijentId;

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    public void setVlasnikSalonaId(Long vlasnikSalonaId) {
        this.vlasnikSalonaId = vlasnikSalonaId;
    }

    public Long getRegistrovaniKlijentId() {
        return registrovaniKlijentId;
    }

    public void setRegistrovaniKlijentId(Long registrovaniKlijentId) {
        this.registrovaniKlijentId = registrovaniKlijentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OcjenaKlijentaId entity = (OcjenaKlijentaId) o;
        return Objects.equals(this.registrovaniKlijentId, entity.registrovaniKlijentId) &&
                Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrovaniKlijentId, vlasnikSalonaId);
    }

}