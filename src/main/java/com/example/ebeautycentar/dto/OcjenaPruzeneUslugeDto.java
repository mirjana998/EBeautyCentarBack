package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import com.example.ebeautycentar.entity.Rezervacija;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link OcjenaPruženeUsluge}
 */
public class OcjenaPruzeneUslugeDto implements Serializable {
    private final Long id;
    private final RegistrovaniKlijentDto registrovaniKlijent;
    private final Integer ocjena;
    private final String komentar;
    private final RezervacijaDto rezervacija;
    private String salonUsluga;

    public OcjenaPruzeneUslugeDto(OcjenaPruženeUsluge ocjena) {
        this.id = ocjena.getId();
        this.registrovaniKlijent = new RegistrovaniKlijentDto(ocjena.getRegistrovaniKlijent());
        this.ocjena = ocjena.getOcjena();
        this.komentar = ocjena.getKomentar();
        this.rezervacija = new RezervacijaDto(ocjena.getRezervacija());
        this.salonUsluga = ocjena.getRezervacija().getZaposleniSalonUsluga().getSalonUsluga().getUsluga().getNaziv();
    }

    public Long getId() {
        return id;
    }

    public RegistrovaniKlijentDto getRegistrovaniKlijent() {
        return registrovaniKlijent;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public String getKomentar() {
        return komentar;
    }

    public RezervacijaDto getRezervacija() {
        return rezervacija;
    }

    public String getSalonUsluga() {
        return salonUsluga;
    }

}