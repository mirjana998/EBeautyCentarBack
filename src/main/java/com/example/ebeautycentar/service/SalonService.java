package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.repository.KorisnikRepository;
import com.example.ebeautycentar.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalonService {

    @Autowired
    private final SalonRepository salonRepository;

    @Autowired
    public SalonService(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    public List<SalonDto> getAllSalon() {
        List<Salon> salonList = salonRepository.findAll();
        List<SalonDto> salonDtoList = new ArrayList<>();
        for(Salon salon : salonList) {
            salonDtoList.add(new SalonDto(salon));
        }
        return salonDtoList;
    }

    public Optional<Salon> getSalonById(Long id) {
        return salonRepository.findById(id);
    }

    public Salon saveSalon(Salon salon) {
        return salonRepository.save(salon);
    }

    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }

    public List<Salon> findByStatusAktivan(String status) {
        return salonRepository.findByStatus("A");
    }

    /*
    public Bicycle updateBicycle(Integer id, Bicycle bicycle) {
        Optional<Bicycle> existingBicycle = bicycleRepository.findById(id);
        Bicycle b = new Bicycle();
        if (existingBicycle.isPresent()) {
            b.setId(bicycle.getId());
            b.setVehicle(bicycle.getVehicle());
            b.setAutonomy(bicycle.getAutonomy());
        }
        return bicycleRepository.save(b);
    }
    */
}
