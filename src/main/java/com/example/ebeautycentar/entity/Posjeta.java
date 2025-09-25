package com.example.ebeautycentar.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Posjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant vrijeme;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    private String ipAdresa;

    private String userAgent; // dodaj ovo

    public Posjeta() {}

    public Posjeta(Salon salon, Instant vrijeme, String ipAdresa, String userAgent) {
        this.salon = salon;
        this.vrijeme = vrijeme;
        this.ipAdresa = ipAdresa;
        this.userAgent = userAgent;
    }

    // getters i setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getVrijeme() { return vrijeme; }
    public void setVrijeme(Instant vrijeme) { this.vrijeme = vrijeme; }

    public Salon getSalon() { return salon; }
    public void setSalon(Salon salon) { this.salon = salon; }

    public String getIpAdresa() { return ipAdresa; }
    public void setIpAdresa(String ipAdresa) { this.ipAdresa = ipAdresa; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
}
