package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.DanUSedmiciDto;
import com.example.ebeautycentar.entity.DanUSedmici;
import com.example.ebeautycentar.repository.DanUSedmiciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DanUSedmiciService {

    @Autowired
    private final DanUSedmiciRepository danUSedmiciRepository;

    @Autowired
    public DanUSedmiciService(DanUSedmiciRepository danUSedmiciRepository) {
        this.danUSedmiciRepository = danUSedmiciRepository;
    }

    public List<DanUSedmiciDto> getAllDanUSedmici() {
        List<DanUSedmici> danUSedmiciList = danUSedmiciRepository.findAll();
        List<DanUSedmiciDto> danUSedmiciDtoList = new ArrayList<>();
        for(DanUSedmici danUSedmici : danUSedmiciList) {
            danUSedmiciDtoList.add(new DanUSedmiciDto(danUSedmici));
        }
        return danUSedmiciDtoList;
    }


    public Optional<DanUSedmici> getDanUSedmiciById(int id) {
        return danUSedmiciRepository.findById(id);
    }


    public DanUSedmici saveKorisnik(DanUSedmici danUSedmici) {
        return danUSedmiciRepository.save(danUSedmici);
    }

    public void deleteDanUSedmici(int id) {
        danUSedmiciRepository.deleteById(id);
    }
}
