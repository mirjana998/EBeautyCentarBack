package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.Rezervacija;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link Rezervacija}
 */
public class RezervacijaDto implements Serializable {
    private final Long id;
    private final Instant vrijemeZakazivanja;
    private final String status;
    private final Instant vrijemeOtkazivanjaVlasnik;
    private final Instant vrijemeOtkazivanjaKlijent;
    private final Long vlasnikSalonaId;
    private final Instant terminPocetkaUsluge;
    private final Long registrovaniKlijentId;
    private final ZaposleniSalonUslugaIdDto zaposleniSalonUslugaId;
    private final Instant terminZavršetkaUsluge;

    public RezervacijaDto(Long id, Instant vrijemeZakazivanja, String status, Instant vrijemeOtkazivanjaVlasnik, Instant vrijemeOtkazivanjaKlijent, Long vlasnikSalonaId, Instant terminPocetkaUsluge, Long registrovaniKlijentId, ZaposleniSalonUslugaIdDto zaposleniSalonUslugaId, Instant terminZavršetkaUsluge) {
        this.id = id;
        this.vrijemeZakazivanja = vrijemeZakazivanja;
        this.status = status;
        this.vrijemeOtkazivanjaVlasnik = vrijemeOtkazivanjaVlasnik;
        this.vrijemeOtkazivanjaKlijent = vrijemeOtkazivanjaKlijent;
        this.vlasnikSalonaId = vlasnikSalonaId;
        this.terminPocetkaUsluge = terminPocetkaUsluge;
        this.registrovaniKlijentId = registrovaniKlijentId;
        this.zaposleniSalonUslugaId = zaposleniSalonUslugaId;
        this.terminZavršetkaUsluge = terminZavršetkaUsluge;
    }

    public RezervacijaDto(Rezervacija rezervacija) {
        this.id = rezervacija.getId();
        this.vrijemeZakazivanja = rezervacija.getVrijemeZakazivanja();
        this.status = rezervacija.getStatus();
        this.vrijemeOtkazivanjaVlasnik = rezervacija.getVrijemeOtkazivanjaVlasnik();
        this.vrijemeOtkazivanjaKlijent = rezervacija.getVrijemeOtkazivanjaKlijent();
        this.vlasnikSalonaId = rezervacija.getVlasnikSalona().getId();
        this.terminPocetkaUsluge = rezervacija.getTerminPocetkaUsluge();
        this.registrovaniKlijentId = rezervacija.getRegistrovaniKlijent().getId();
        this.zaposleniSalonUslugaId = new ZaposleniSalonUslugaIdDto(rezervacija.getZaposleniSalonUsluga().getId());
        this.terminZavršetkaUsluge = rezervacija.getTerminZavršetkaUsluge();
    }

    public Long getId() {
        return id;
    }

    public Instant getVrijemeZakazivanja() {
        return vrijemeZakazivanja;
    }

    public String getStatus() {
        return status;
    }

    public Instant getVrijemeOtkazivanjaVlasnik() {
        return vrijemeOtkazivanjaVlasnik;
    }

    public Instant getVrijemeOtkazivanjaKlijent() {
        return vrijemeOtkazivanjaKlijent;
    }

    public Long getVlasnikSalonaId() {
        return vlasnikSalonaId;
    }

    public Instant getTerminPocetkaUsluge() {
        return terminPocetkaUsluge;
    }

    public Long getRegistrovaniKlijentId() {
        return registrovaniKlijentId;
    }

    public ZaposleniSalonUslugaIdDto getZaposleniSalonUslugaId() {
        return zaposleniSalonUslugaId;
    }

    public Instant getTerminZavršetkaUsluge() {
        return terminZavršetkaUsluge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RezervacijaDto entity = (RezervacijaDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.vrijemeZakazivanja, entity.vrijemeZakazivanja) &&
                Objects.equals(this.status, entity.status) &&
                Objects.equals(this.vrijemeOtkazivanjaVlasnik, entity.vrijemeOtkazivanjaVlasnik) &&
                Objects.equals(this.vrijemeOtkazivanjaKlijent, entity.vrijemeOtkazivanjaKlijent) &&
                Objects.equals(this.vlasnikSalonaId, entity.vlasnikSalonaId) &&
                Objects.equals(this.terminPocetkaUsluge, entity.terminPocetkaUsluge) &&
                Objects.equals(this.registrovaniKlijentId, entity.registrovaniKlijentId) &&
                Objects.equals(this.zaposleniSalonUslugaId, entity.zaposleniSalonUslugaId) &&
                Objects.equals(this.terminZavršetkaUsluge, entity.terminZavršetkaUsluge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vrijemeZakazivanja, status, vrijemeOtkazivanjaVlasnik, vrijemeOtkazivanjaKlijent, vlasnikSalonaId, terminPocetkaUsluge, registrovaniKlijentId, zaposleniSalonUslugaId, terminZavršetkaUsluge);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "vrijemeZakazivanja = " + vrijemeZakazivanja + ", " +
                "status = " + status + ", " +
                "vrijemeOtkazivanjaVlasnik = " + vrijemeOtkazivanjaVlasnik + ", " +
                "vrijemeOtkazivanjaKlijent = " + vrijemeOtkazivanjaKlijent + ", " +
                "vlasnikSalonaId = " + vlasnikSalonaId + ", " +
                "terminPocetkaUsluge = " + terminPocetkaUsluge + ", " +
                "registrovaniKlijentId = " + registrovaniKlijentId + ", " +
                "zaposleniSalonUslugaId = " + zaposleniSalonUslugaId + ", " +
                "terminZavršetkaUsluge = " + terminZavršetkaUsluge + ")";
    }
}