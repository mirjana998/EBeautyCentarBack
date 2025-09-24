package com.example.ebeautycentar.dto;

import com.example.ebeautycentar.entity.RadnoVrijeme;
import org.springframework.data.relational.core.sql.In;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

/**
 * DTO for {@link RadnoVrijeme}
 */
public class RadnoVrijemePutDto implements Serializable {
    private  Long id;
    private Integer danUSedmici;
    private  String pocetakRadnoVrijeme;
    private  String krajRadnoVrijeme;
    private Long salonId;

    public RadnoVrijemePutDto() {
    }


    public Long getId() {
        return id;
    }

    public String getPocetakRadnoVrijeme() {
        return pocetakRadnoVrijeme;
    }

    public String getKrajRadnoVrijeme() {
        return krajRadnoVrijeme;
    }

    public Long getSalonId() {
        return salonId;
    }

    public Integer getDanUSedmici() {
        return danUSedmici;
    }

}