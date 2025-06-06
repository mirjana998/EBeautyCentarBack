package com.example.ebeautycentar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ZaposleniSalonUslugaId implements Serializable {
    private static final long serialVersionUID = -6605522585458653885L;
    @Column(name = "zaposleni_id", nullable = false)
    private Long zaposleniId;

    @Column(name = "salon_usluga_id", nullable = false)
    private Long salonUslugaId;

    public Long getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Long zaposleniId) {
        this.zaposleniId = zaposleniId;
    }

    public Long getSalonUslugaId() {
        return salonUslugaId;
    }

    public void setSalonUslugaId(Long salonUslugaId) {
        this.salonUslugaId = salonUslugaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ZaposleniSalonUslugaId entity = (ZaposleniSalonUslugaId) o;
        return Objects.equals(this.zaposleniId, entity.zaposleniId) &&
                Objects.equals(this.salonUslugaId, entity.salonUslugaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zaposleniId, salonUslugaId);
    }

}