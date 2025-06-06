package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.OcjenaKlijentaDto;
import com.example.ebeautycentar.entity.OcjenaKlijenta;
import com.example.ebeautycentar.repository.OcjenaKlijentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OcjenaKlijentaService {

    @Autowired
    private final OcjenaKlijentaRepository ocjenaKlijentaRepository;

    @Autowired
    public OcjenaKlijentaService(OcjenaKlijentaRepository ocjenaKlijentaRepository) {
        this.ocjenaKlijentaRepository = ocjenaKlijentaRepository;
    }

    public List<OcjenaKlijentaDto> getAllOcjenaKlijenta() {
        List<OcjenaKlijenta> ocjenaKlijentaList = ocjenaKlijentaRepository.findAll();
        List<OcjenaKlijentaDto> ocjenaKlijentaDtoList = new ArrayList<>();
        for(OcjenaKlijenta ocjenaKlijenta : ocjenaKlijentaList) {
            ocjenaKlijentaDtoList.add(new OcjenaKlijentaDto(ocjenaKlijenta));
        }
        return ocjenaKlijentaDtoList;
    }


    public Optional<OcjenaKlijenta> getByVlasnikSalonaIdAndRegistrovaniKlijentId(Long vlasnikSalonaId, Long registrovaniKlijentId) {
        return ocjenaKlijentaRepository.findByVlasnikSalonaIdAndRegistrovaniKlijentId(vlasnikSalonaId,registrovaniKlijentId);
    }

    public OcjenaKlijenta saveOcjenaKlijenta(OcjenaKlijenta ocjenaKlijenta) {
        return ocjenaKlijentaRepository.save(ocjenaKlijenta);
    }

}
