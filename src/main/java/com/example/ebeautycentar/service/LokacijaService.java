package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.LokacijaDto;
import com.example.ebeautycentar.entity.Lokacija;
import com.example.ebeautycentar.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LokacijaService {

    @Autowired
    private final LokacijaRepository lokacijaRepository;

    @Autowired
    public LokacijaService(LokacijaRepository lokacijaRepository) {
        this.lokacijaRepository = lokacijaRepository;
    }

    public List<LokacijaDto> getAllLokacija() {
        List<Lokacija> lokacijaList = lokacijaRepository.findAll();
        List<LokacijaDto> lokacijaDtoList = new ArrayList<>();
        for(Lokacija lokacija : lokacijaList) {
            lokacijaDtoList.add(new LokacijaDto(lokacija));
        }
        return lokacijaDtoList;
    }


    public Optional<Lokacija> getLokacijaById(Long id) {
        return lokacijaRepository.findById(id);
    }


    public Lokacija saveLokacija(Lokacija lokacija) {
        return lokacijaRepository.save(lokacija);
    }

    public void deleteLokacija(Long id) {
        lokacijaRepository.deleteById(id);
    }

    public List<String> getSviGradovi() {
        return lokacijaRepository.findDistinctGrad();
    }

}
