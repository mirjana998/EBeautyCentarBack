package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.OcjenaPruzeneUslugeDto;
import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import com.example.ebeautycentar.repository.OcjenaPruženeUslugeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OcjenaPruzeneUslugeService {

    @Autowired
    private final OcjenaPruženeUslugeRepository ocjenaPruženeUslugeRepository;

    @Autowired
    public OcjenaPruzeneUslugeService(OcjenaPruženeUslugeRepository ocjenaPruženeUslugeRepository) {
        this.ocjenaPruženeUslugeRepository = ocjenaPruženeUslugeRepository;
    }

    public List<OcjenaPruzeneUslugeDto> getAllOcjenaPruženeUsluge() {
        List<OcjenaPruženeUsluge> ocjenaPruženeUslugeList = ocjenaPruženeUslugeRepository.findAll();
        List<OcjenaPruzeneUslugeDto> ocjenaPruženeUslugeDtoList = new ArrayList<>();
        for(OcjenaPruženeUsluge ocjenaPruženeUsluge : ocjenaPruženeUslugeList) {
            ocjenaPruženeUslugeDtoList.add(new OcjenaPruzeneUslugeDto(ocjenaPruženeUsluge));
        }
        return ocjenaPruženeUslugeDtoList;
    }


    public Optional<OcjenaPruženeUsluge> getOcjenaPruženeUslugeById(Long id) {
        return ocjenaPruženeUslugeRepository.findById(id);
    }



    public OcjenaPruženeUsluge saveOcjenaPruženeUsluge(OcjenaPruženeUsluge ocjenaPruženeUsluge) {
        return ocjenaPruženeUslugeRepository.save(ocjenaPruženeUsluge);
    }

    public void deleteOcjena(Long id) {
        ocjenaPruženeUslugeRepository.deleteById(id);
    }


}
