package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.OcjenaKlijenta;
import com.example.ebeautycentar.entity.OcjenaKlijentaId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link OcjenaKlijentaId}
 */
public class OcjenaKlijentaIdDto implements Serializable {
    private final Long vlasnikSalonaId;
    private final Long registrovaniKlijentId;

    public OcjenaKlijentaIdDto(Long vlasnikSalonaId, Long registrovaniKlijentId) {
        this.vlasnikSalonaId = vlasnikSalonaId;
        this.registrovaniKlijentId = registrovaniKlijentId;
    }

    public OcjenaKlijentaIdDto(OcjenaKlijenta ocjenaKlijenta) {
        this.vlasnikSalonaId = ocjenaKlijenta.getVlasnikSalona().getId();
        this.registrovaniKlijentId = ocjenaKlijenta.getRegistrovaniKlijent().getId();
    }

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    public Long getRegistrovaniKlijentId() {
        return registrovaniKlijentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcjenaKlijentaIdDto entity = (OcjenaKlijentaIdDto) o;
        return Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId) &&
                Objects.equals(this.registrovaniKlijentId, entity.registrovaniKlijentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vlasnikSalonaId, registrovaniKlijentId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "vlasnikSalonaId = " + vlasnikSalonaId + ", " +
                "registrovaniKlijentId = " + registrovaniKlijentId + ")";
    }
}