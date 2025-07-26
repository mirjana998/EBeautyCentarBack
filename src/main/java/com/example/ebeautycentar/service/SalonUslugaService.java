package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonUslugaDodajDto;
import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.SalonUsluga;
import com.example.ebeautycentar.entity.Usluga;
import com.example.ebeautycentar.repository.KorisnikRepository;
import com.example.ebeautycentar.repository.SalonRepository;
import com.example.ebeautycentar.repository.SalonUslugaRepository;
import com.example.ebeautycentar.repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalonUslugaService {

    @Autowired
    private final SalonUslugaRepository salonUslugaRepository;
    private final SalonRepository salonRepository;
    private final UslugaRepository uslugaRepository;

    @Autowired
    public SalonUslugaService(SalonUslugaRepository salonUslugaRepository,SalonRepository salonRepository,UslugaRepository uslugaRepository) {
        this.salonUslugaRepository = salonUslugaRepository;
        this.salonRepository = salonRepository;
        this.uslugaRepository = uslugaRepository;
    }

    public List<SalonUslugaDto> getAllSalonUsluga() {
        List<SalonUsluga> salonUslugaList = salonUslugaRepository.findAll();
        List<SalonUslugaDto> salonUslugaDtoList = new ArrayList<>();
        for(SalonUsluga salonUsluga : salonUslugaList) {
            salonUslugaDtoList.add(new SalonUslugaDto(salonUsluga));
        }
        return salonUslugaDtoList;
    }


    public Optional<SalonUsluga> getSalonUslugaById(Long id) {
        return salonUslugaRepository.findById(id);
    }

    public List<SalonUslugaDto> getSalonUslugaBySalonId(Long id) {
        List<SalonUsluga> salonUsluge = salonUslugaRepository.findBySalonId(id);
        List<SalonUslugaDto> salonUslugaDtoList = new ArrayList<>();
        for(SalonUsluga s : salonUsluge) {
            salonUslugaDtoList.add(new SalonUslugaDto(s));
        }
        return salonUslugaDtoList;
    }


    public SalonUsluga saveSalonUsluga(SalonUsluga salonUsluga) {
        return salonUslugaRepository.save(salonUsluga);
    }

    public void deleteSalonUsluga(Long id) {
        salonUslugaRepository.deleteById(id);
    }

    public List<SalonUsluga> findByStatusAktivan(String status) {
        return salonUslugaRepository.findByStatus("A");
    }

    public SalonUslugaDto kreirajSalonUslugu(SalonUslugaDodajDto dto) {
        Salon salon = salonRepository.findByNaziv(dto.getNazivSalona())
                .orElseThrow(() -> new RuntimeException("Salon nije pronađen"));

        Usluga usluga = uslugaRepository.findByNaziv(dto.getNazivUsluge())
                .orElseThrow(() -> new RuntimeException("Usluga nije pronađena"));

        // Formiramo entitet za insert
        SalonUsluga nova = new SalonUsluga();
        nova.setSalon(salon);
        nova.setUsluga(usluga);
        nova.setTrajanjeUsluge(dto.getTrajanje_usluge());
        nova.setCijena(dto.getCijena());
        nova.setOpis(dto.getOpis());

        // Postavljamo defaultne vrijednosti
        nova.setDatumPocetka(null);
        nova.setDatumKraja(null);
        nova.setStatus("A");

        salonUslugaRepository.save(nova);

        return new SalonUslugaDto(nova);
    }
}
