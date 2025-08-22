package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link OcjenaPruženeUsluge}
 */
public class OcjenaPruzeneUslugeKlijentDto implements Serializable {
    private final Long id;
    private final String clerkUserId;
    private final Integer ocjena;
    private final String komentar;
    private final Long rezervacijaId;

    public OcjenaPruzeneUslugeKlijentDto(Long id, String clerkUserId, Integer ocjena, String komentar, Long rezervacijaId) {
        this.id = id;
        this.clerkUserId = clerkUserId;
        this.ocjena = ocjena;
        this.komentar = komentar;
        this.rezervacijaId = rezervacijaId;
    }

    public Long getId() {
        return id;
    }

    public String getClerkUserId() {
        return clerkUserId;
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


}