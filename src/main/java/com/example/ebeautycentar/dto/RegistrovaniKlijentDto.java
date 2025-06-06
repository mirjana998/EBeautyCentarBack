package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.RegistrovaniKlijent;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link RegistrovaniKlijent}
 */
public class RegistrovaniKlijentDto implements Serializable {
    private final Long id;
    private final KorisnikDto korisnik;
    private final Integer brojTermina;

    public RegistrovaniKlijentDto(RegistrovaniKlijent registrovaniKlijent) {
        this.id = registrovaniKlijent.getId();
        this.korisnik = new KorisnikDto(registrovaniKlijent.getKorisnik());
        this.brojTermina = registrovaniKlijent.getBrojTermina();
    }

    public Long getId() {
        return id;
    }

    public KorisnikDto getKorisnik() {
        return korisnik;
    }

    public Integer getBrojTermina() {
        return brojTermina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrovaniKlijentDto entity = (RegistrovaniKlijentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.korisnik, entity.korisnik) &&
                Objects.equals(this.brojTermina, entity.brojTermina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnik, brojTermina);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "korisnik = " + korisnik + ", " +
                "brojTermina = " + brojTermina + ")";
    }
}