package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.UslugaDto;
import com.example.ebeautycentar.entity.Usluga;
import com.example.ebeautycentar.repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UslugaService {

    @Autowired
    private final UslugaRepository uslugaRepository;

    @Autowired
    public UslugaService(UslugaRepository uslugaRepository) {
        this.uslugaRepository = uslugaRepository;
    }

    public List<UslugaDto> getAllUsluga() {
        List<Usluga> uslugaList = uslugaRepository.findAll();
        List<UslugaDto> uslugaDtoList = new ArrayList<>();
        for(Usluga usluga : uslugaList) {
            uslugaDtoList.add(new UslugaDto(usluga));
        }
        return uslugaDtoList;
    }


    public Optional<Usluga> getUslugaById(Long id) {
        return uslugaRepository.findById(id);
    }


    public Usluga saveUsluga(Usluga usluga) {
        return uslugaRepository.save(usluga);
    }

    public void deleteUsluga(Long id) {
        uslugaRepository.deleteById(id);
    }

    public List<Usluga> findByStatusAktivan(String status) {
        return uslugaRepository.findByStatus("A");
    }

    public List<String> getSveUsluge() {
        return uslugaRepository.findDistinctNaziv();
    }

    public Usluga nadjiPoNazivu(String naziv) {
        return uslugaRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Usluga nije pronađena"));
    }
}
