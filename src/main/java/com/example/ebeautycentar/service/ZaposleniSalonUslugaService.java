package com.example.ebeautycentar.service;


import com.example.ebeautycentar.entity.ZaposleniSalonUsluga;
import com.example.ebeautycentar.dto.ZaposleniSalonUslugaDto;
import com.example.ebeautycentar.repository.ZaposleniSalonUslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZaposleniSalonUslugaService {

    @Autowired
    private final ZaposleniSalonUslugaRepository zaposleniSalonUslugaRepository;

    @Autowired
    public ZaposleniSalonUslugaService(ZaposleniSalonUslugaRepository zaposleniSalonUslugaRepository) {
        this.zaposleniSalonUslugaRepository = zaposleniSalonUslugaRepository;
    }

    public List<ZaposleniSalonUslugaDto> getAllZaposleniSalonUsluga() {
        List<ZaposleniSalonUsluga> zaposleniSalonUslugaList = zaposleniSalonUslugaRepository.findAll();
        List<ZaposleniSalonUslugaDto> zaposleniSalonUslugaDtoList = new ArrayList<>();
        for(ZaposleniSalonUsluga zaposleniSalonUsluga : zaposleniSalonUslugaList) {
            zaposleniSalonUslugaDtoList.add(new ZaposleniSalonUslugaDto(zaposleniSalonUsluga));
        }
        return zaposleniSalonUslugaDtoList;
    }


    public Optional<ZaposleniSalonUsluga> getZaposleniSalonUslugaById(Long id) {
        return zaposleniSalonUslugaRepository.findById(id);
    }


    public ZaposleniSalonUsluga saveZaposleniSalonUsluga(ZaposleniSalonUsluga zaposleniSalonUsluga) {
        return zaposleniSalonUslugaRepository.save(zaposleniSalonUsluga);
    }

}
