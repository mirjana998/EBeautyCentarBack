package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.OcjenaKlijenta;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link OcjenaKlijenta}
 */
public class OcjenaKlijentaDto implements Serializable {
    private final Long vlasnikSalonaId;
    private final Long registrovaniKlijentId;
    private final Integer ocjena;

    public OcjenaKlijentaDto(Long vlasnikSalonaId, Long registrovaniKlijentId, Integer ocjena) {
        this.vlasnikSalonaId = vlasnikSalonaId;
        this.registrovaniKlijentId = registrovaniKlijentId;
        this.ocjena = ocjena;
    }

    public OcjenaKlijentaDto(OcjenaKlijenta ocjenaKlijenta) {
        this.vlasnikSalonaId = ocjenaKlijenta.getVlasnikSalona().getId();
        this.registrovaniKlijentId = ocjenaKlijenta.getRegistrovaniKlijent().getId();
        this.ocjena = ocjenaKlijenta.getOcjena();
    }

    public Long getvlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    public Long getRegistrovaniKlijentId() {
        return registrovaniKlijentId;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcjenaKlijentaDto entity = (OcjenaKlijentaDto) o;
        return Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId) &&
                Objects.equals(this.registrovaniKlijentId, entity.registrovaniKlijentId) &&
                Objects.equals(this.ocjena, entity.ocjena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vlasnikSalonaId, registrovaniKlijentId, ocjena);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idVlasnikSalonaId = " + vlasnikSalonaId + ", " +
                "idRegistrovaniKlijentId = " + registrovaniKlijentId + ", " +
                "ocjena = " + ocjena + ")";
    }
}