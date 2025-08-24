package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Zaposleni;
import com.example.ebeautycentar.repository.KorisnikRepository;
import com.example.ebeautycentar.repository.ZaposleniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZaposleniService {

    @Autowired
    private final ZaposleniRepository zaposleniRepository;

    @Autowired
    public ZaposleniService(ZaposleniRepository zaposleniRepository) {
        this.zaposleniRepository = zaposleniRepository;
    }

    public List<ZaposleniDto> getAllZaposleni() {
        List<Zaposleni> zaposleniList = zaposleniRepository.findAll();
        List<ZaposleniDto> zaposleniDtoList = new ArrayList<>();
        for(Zaposleni zaposleni : zaposleniList) {
            zaposleniDtoList.add(new ZaposleniDto(zaposleni));
        }
        return zaposleniDtoList;
    }

    public List<Zaposleni> findBySalonAndStatus(long salonId) {
       return  zaposleniRepository.findBySalonIdAndAktivan(salonId,"A");

    }

    public List<Zaposleni> findBySalon(long salonId) {
        return  zaposleniRepository.findBySalonId(salonId);

    }


    public Optional<Zaposleni> getZaposleniById(Long id) {
        return zaposleniRepository.findById(id);
    }


    public Zaposleni saveZaposleni(Zaposleni zaposleni) {
        return zaposleniRepository.save(zaposleni);
    }

    public void deleteZaposleni(Long id) {
        zaposleniRepository.deleteById(id);
    }

    public List<Zaposleni> findByAktivan(String aktivan) {
        return zaposleniRepository.findByAktivan(aktivan);
    }
}
