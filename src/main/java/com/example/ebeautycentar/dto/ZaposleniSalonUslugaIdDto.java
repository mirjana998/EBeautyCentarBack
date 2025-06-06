package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.ZaposleniSalonUslugaId;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link ZaposleniSalonUslugaId}
 */
public class ZaposleniSalonUslugaIdDto implements Serializable {
    private final Long zaposleniId;
    private final Long salonUslugaId;

    public ZaposleniSalonUslugaIdDto(Long zaposleniId, Long salonUslugaId) {
        this.zaposleniId = zaposleniId;
        this.salonUslugaId = salonUslugaId;
    }

    public ZaposleniSalonUslugaIdDto(ZaposleniSalonUslugaId zaposleniSalonUslugaId) {
        this.zaposleniId = zaposleniSalonUslugaId.getZaposleniId();
        this.salonUslugaId = zaposleniSalonUslugaId.getSalonUslugaId();
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
        ZaposleniSalonUslugaIdDto entity = (ZaposleniSalonUslugaIdDto) o;
        return Objects.equals(this.zaposleniId, entity.zaposleniId) &&
                Objects.equals(this.salonUslugaId, entity.salonUslugaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zaposleniId, salonUslugaId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "zaposleniId = " + zaposleniId + ", " +
                "salonUslugaId = " + salonUslugaId + ")";
    }
}