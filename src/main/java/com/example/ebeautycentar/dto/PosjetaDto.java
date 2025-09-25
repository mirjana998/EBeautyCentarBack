package com.example.ebeautycentar.dto;

import java.time.Instant;

public class PosjetaDto {
    private Long id;
    private Long salonId;       // može biti null za globalne posjete
    private Instant vrijeme;
    private String ipAdresa;
    private String userAgent;

    public PosjetaDto() {}

    // Konstruktor za globalnu posjetu
    public PosjetaDto(Long id, String ipAdresa, String userAgent, Instant vrijeme) {
        this.id = id;
        this.vrijeme = vrijeme;
        this.ipAdresa = ipAdresa;
        this.userAgent = userAgent;
    }

    // Konstruktor za posjetu salonu
    public PosjetaDto(Long id, Long salonId, Instant vrijeme) {
        this.id = id;
        this.salonId = salonId;
        this.vrijeme = vrijeme;
    }

    // getters i setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSalonId() { return salonId; }
    public void setSalonId(Long salonId) { this.salonId = salonId; }

    public Instant getVrijeme() { return vrijeme; }
    public void setVrijeme(Instant vrijeme) { this.vrijeme = vrijeme; }

    public String getIpAdresa() { return ipAdresa; }
    public void setIpAdresa(String ipAdresa) { this.ipAdresa = ipAdresa; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
}
