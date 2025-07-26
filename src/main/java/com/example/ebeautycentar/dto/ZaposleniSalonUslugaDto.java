package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.ZaposleniSalonUsluga;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link ZaposleniSalonUsluga}
 */
public class ZaposleniSalonUslugaDto implements Serializable {
    private final Long id;
    private final Long zaposleniId;
    private final Long salonUslugaId;

    public ZaposleniSalonUslugaDto(Long id, Long zaposleniId, Long salonUslugaId) {
        this.id = id;
        this.zaposleniId = zaposleniId;
        this.salonUslugaId = salonUslugaId;
    }

    public ZaposleniSalonUslugaDto(ZaposleniSalonUsluga zaposleniSalonUsluga) {
        this.id = zaposleniSalonUsluga.getId();
        this.zaposleniId = zaposleniSalonUsluga.getZaposleni().getId();
        this.salonUslugaId = zaposleniSalonUsluga.getSalonUsluga().getId();
    }

    public Long getId() {
        return id;
    }

    public Long getZaposleniId() {
        return zaposleniId;
    }

    public Long getSalonUslugaId() {
        return salonUslugaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZaposleniSalonUslugaDto entity = (ZaposleniSalonUslugaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.zaposleniId, entity.zaposleniId) &&
                Objects.equals(this.salonUslugaId, entity.salonUslugaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zaposleniId, salonUslugaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "zaposleniId = " + zaposleniId + ", " +
                "salonUslugaId = " + salonUslugaId + ")";
    }
}