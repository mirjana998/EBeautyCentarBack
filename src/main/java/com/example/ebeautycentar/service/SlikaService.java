package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.SlikaDto;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.repository.SlikaRepository;
import com.example.ebeautycentar.repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlikaService {

    @Autowired
    private final SlikaRepository slikaRepository;

    @Autowired
    private final UslugaRepository uslugaRepository;

    @Autowired
    public SlikaService(SlikaRepository slikaRepository, UslugaRepository uslugaRepository) {
        this.slikaRepository = slikaRepository;
        this.uslugaRepository= uslugaRepository;
    }

    public List<SlikaDto> getAllSlika() {
        List<Slika> slikaList = slikaRepository.findAll();
        List<SlikaDto> slikaDtoList = new ArrayList<>();
        for(Slika slika : slikaList) {
            slikaDtoList.add(new SlikaDto(slika));
        }
        return slikaDtoList;
    }

    public List<Slika> getBySalonIdAndStatusAndVrsta(Long salonId, String vrsta) {
        return this.slikaRepository.findBySalonIdAndStatusAndVrsta(salonId, "A", vrsta);
    }


    public Optional<Slika> getSlikaById(Long id) {
        return slikaRepository.findById(id);
    }

    public Optional<Slika> getSlikaByUslugaId(Long uslugaId) {
        return slikaRepository.findByUslugaId(uslugaId);
    }


    public Slika saveSlika(Slika slika) {
        return slikaRepository.save(slika);
    }

    public void deleteSlika(Long id) {
        slikaRepository.deleteById(id);
    }

    public List<Slika> findByStatusAktivan(String status) {
        return slikaRepository.findByStatus("A");
    }

}
