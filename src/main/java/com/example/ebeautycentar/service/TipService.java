package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.TipDto;
import com.example.ebeautycentar.entity.Tip;
import com.example.ebeautycentar.repository.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipService {

    @Autowired
    private final TipRepository tipRepository;

    @Autowired
    public TipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    public List<TipDto> getAllTip() {
        List<Tip> tipList = tipRepository.findAll();
        List<TipDto> tipDtoList = new ArrayList<>();
        for(Tip tip : tipList) {
            tipDtoList.add(new TipDto(tip));
        }
        return tipDtoList;
    }


    public Optional<Tip> getTipById(int id) {
        return tipRepository.findById(id);
    }


    public Tip saveTip(Tip tip) {
        return tipRepository.save(tip);
    }

    public void deleteTip(int id) {
        tipRepository.deleteById(id);
    }

    public List<Tip> findByStatusAktivan(String status) {
        return tipRepository.findByStatus("A");
    }
}
