package com.example.ebeautycentar.service;

import com.example.ebeautycentar.entity.Posjeta;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.repository.PosjetaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class PosjetaService {

    private final PosjetaRepository posjetaRepository;

    public PosjetaService(PosjetaRepository posjetaRepository) {
        this.posjetaRepository = posjetaRepository;
    }

    // Loguje posjetu za određeni salon
    public Posjeta savePosjeta(Salon salon, String ip) {
        Posjeta posjeta = new Posjeta();
        posjeta.setSalon(salon);
        posjeta.setIpAdresa(ip);
        posjeta.setVrijeme(Instant.now());
        return posjetaRepository.save(posjeta);
    }

    // Loguje globalnu posjetu (bez salona)
    public Posjeta savePosjeta(Posjeta posjeta) {
        if (posjeta.getVrijeme() == null) {
            posjeta.setVrijeme(Instant.now());
        }
        return posjetaRepository.save(posjeta);
    }

    // Vrati sve posjete
    public List<Posjeta> getAllPosjete() {
        return posjetaRepository.findAll();
    }

    // Vrati sve posjete za određeni salon
    public List<Posjeta> getAllPosjeteForSalon(Long salonId) {
        return posjetaRepository.findBySalonId(salonId);
    }

    // Vrati posjete za salon za određeni dan
    public List<Posjeta> getPosjeteForSalonByDate(Long salonId, LocalDate date) {
        return posjetaRepository.findBySalonAndDate(salonId, date);
    }

    // Broj posjeta za salon za određeni dan
    public int countPosjetaForSalonByDate(Long salonId, LocalDate date) {
        return posjetaRepository.countBySalonAndDate(salonId, date);
    }
}
