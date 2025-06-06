package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.RadnoVrijemeDto;
import com.example.ebeautycentar.entity.RadnoVrijeme;
import com.example.ebeautycentar.repository.RadnoVrijemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RadnoVrijemeService {

    @Autowired
    private final RadnoVrijemeRepository radnoVrijemeRepository;

    @Autowired
    public RadnoVrijemeService(RadnoVrijemeRepository radnoVrijemeRepository) {
        this.radnoVrijemeRepository = radnoVrijemeRepository;
    }

    public List<RadnoVrijemeDto> getAllRadnoVrijeme() {
        List<RadnoVrijeme> radnoVrijemeList = radnoVrijemeRepository.findAll();
        List<RadnoVrijemeDto> radnoVrijemeDtoList = new ArrayList<>();
        for(RadnoVrijeme radnoVrijeme : radnoVrijemeList) {
            radnoVrijemeDtoList.add(new RadnoVrijemeDto(radnoVrijeme));
        }
        return radnoVrijemeDtoList;
    }


    public Optional<RadnoVrijeme> getRadnoVrijemeById(Long id) {
        return radnoVrijemeRepository.findById(id);
    }


    public RadnoVrijeme saveRadnoVrijeme(RadnoVrijeme radnoVrijeme) {
        return radnoVrijemeRepository.save(radnoVrijeme);
    }

    public void deleteRadnoVrijeme(Long id) {
        radnoVrijemeRepository.deleteById(id);
    }
}
