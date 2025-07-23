package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.SalonUsluga;
import com.example.ebeautycentar.repository.KorisnikRepository;
import com.example.ebeautycentar.repository.SalonUslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalonUslugaService {

    @Autowired
    private final SalonUslugaRepository salonUslugaRepository;

    @Autowired
    public SalonUslugaService(SalonUslugaRepository salonUslugaRepository) {
        this.salonUslugaRepository = salonUslugaRepository;
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
}
