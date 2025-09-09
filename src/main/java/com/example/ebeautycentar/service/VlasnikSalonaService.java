package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.VlasnikSalonaDto;
import com.example.ebeautycentar.entity.VlasnikSalona;
import com.example.ebeautycentar.repository.VlasnikSalonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VlasnikSalonaService {

    @Autowired
    private final VlasnikSalonaRepository vlasnikSalonaRepository;

    @Autowired
    public VlasnikSalonaService(VlasnikSalonaRepository vlasnikSalonaRepository) {
        this.vlasnikSalonaRepository = vlasnikSalonaRepository;
    }

    public List<VlasnikSalonaDto> getAllKorisnik() {
        List<VlasnikSalona> vlasnikSalonaList = vlasnikSalonaRepository.findAll();
        List<VlasnikSalonaDto> vlasnikSalonaDtoList = new ArrayList<>();
        for(VlasnikSalona vlasnikSalona : vlasnikSalonaList) {
            vlasnikSalonaDtoList.add(new VlasnikSalonaDto(vlasnikSalona));
        }
        return vlasnikSalonaDtoList;
    }

    public Optional<VlasnikSalona> findById(Long id) {
        return vlasnikSalonaRepository.findById(id);
    }


    public Optional<VlasnikSalona> getVlasnikSalonaById(Long id) {
        return vlasnikSalonaRepository.findById(id);
    }


    public VlasnikSalona saveVlasnikSalona(VlasnikSalona vlasnikSalona) {
        return vlasnikSalonaRepository.save(vlasnikSalona);
    }

    public void deleteVlasnikSalona(Long id) {
        vlasnikSalonaRepository.deleteById(id);
    }


}
