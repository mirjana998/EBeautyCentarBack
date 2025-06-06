package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link OcjenaPruženeUsluge}
 */
public class OcjenaPruzeneUslugeDto implements Serializable {
    private final Long id;
    private final Long registrovaniKlijentId;
    private final Integer ocjena;
    private final String komentar;
    private final Long rezervacijaId;

    public OcjenaPruzeneUslugeDto(OcjenaPruženeUsluge ocjena) {
        this.id = ocjena.getId();
        this.registrovaniKlijentId = ocjena.getRegistrovaniKlijent().getId();
        this.ocjena = ocjena.getOcjena();
        this.komentar = ocjena.getKomentar();
        this.rezervacijaId = ocjena.getRezervacija().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getRegistrovaniKlijentId() {
        return registrovaniKlijentId;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public String getKomentar() {
        return komentar;
    }

    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcjenaPruzeneUslugeDto entity = (OcjenaPruzeneUslugeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.registrovaniKlijentId, entity.registrovaniKlijentId) &&
                Objects.equals(this.ocjena, entity.ocjena) &&
                Objects.equals(this.komentar, entity.komentar) &&
                Objects.equals(this.rezervacijaId, entity.rezervacijaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrovaniKlijentId, ocjena, komentar, rezervacijaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "registrovaniKlijentId = " + registrovaniKlijentId + ", " +
                "ocjena = " + ocjena + ", " +
                "komentar = " + komentar + ", " +
                "rezervacijaId = " + rezervacijaId + ")";
    }
}