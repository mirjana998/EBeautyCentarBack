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
import java.util.stream.Collectors;

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

    public List<SalonDto> getSaloniByLokacijaIUsluga(String grad, String usluga) {
        List<Salon> saloni = salonRepository.findByGradAndUsluga(grad, usluga,"A");
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }
    public List<SalonDto> findByNazivAndStatus(String naziv,String status) {
        List<Salon> saloni = salonRepository.findByNazivLikeAndStatusLike(naziv,status);
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public List<SalonDto> findByGradAndStatus(String grad,String status)
    {
        List<Salon> saloni = salonRepository.findByGradAndStatus(grad,status);
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public List<SalonDto> getSaloniByGradINaziv(String grad, String naziv) {
        List<Salon> saloni = salonRepository.findByGradAndNaziv(grad, "%"+naziv+"%","A");
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public List<SalonDto>findByUslugaAndStatus(String usluga,String status)
    {
        List<Salon> saloni=salonRepository.findByUslugaAndStatus(usluga,status);
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public List<SalonDto>getSaloniByUslugaAndNaziv(String usluga,String naziv)
    {
        List<Salon>saloni=salonRepository.findByUslugaAndNaziv(usluga,"%"+naziv+"%","A");
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }
    public List<SalonDto>getSaloniByGradAndUslugaAndNaziv(String grad,String usluga,String naziv)
    {
        List<Salon>saloni=salonRepository.findByGradAndUslugaAndNaziv(grad,usluga,"%"+naziv+"%","A");
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }
}
