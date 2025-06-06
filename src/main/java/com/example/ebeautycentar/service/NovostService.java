package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.NovostDto;
import com.example.ebeautycentar.entity.Novost;
import com.example.ebeautycentar.repository.NovostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NovostService {

    @Autowired
    private final NovostRepository novostRepository;

    @Autowired
    public NovostService(NovostRepository novostRepository) {
        this.novostRepository = novostRepository;
    }

    public List<NovostDto> getAllNovost() {
        List<Novost> novostList = novostRepository.findAll();
        List<NovostDto> novostDtoList = new ArrayList<>();
        for(Novost korisnik : novostList) {
            novostDtoList.add(new NovostDto(korisnik));
        }
        return novostDtoList;
    }


    public Optional<Novost> getNovostById(Long id) {
        return novostRepository.findById(id);
    }


    public Novost saveNovost(Novost novost) {
        return novostRepository.save(novost);
    }

    public void deleteNovost(Long id) {
        novostRepository.deleteById(id);
    }

    public List<Novost> findByStatusAktivan(String status) {
        return novostRepository.findByStatus("A");
    }
}
