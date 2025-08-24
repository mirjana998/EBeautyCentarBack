package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.NotifikacijaDto;
import com.example.ebeautycentar.entity.Notifikacija;
import com.example.ebeautycentar.repository.NotifikacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotifikacijaService {

    @Autowired
    private final NotifikacijaRepository notifikacijaRepository;

    @Autowired
    public NotifikacijaService(NotifikacijaRepository notifikacijaRepository) {
        this.notifikacijaRepository = notifikacijaRepository;
    }

    public List<NotifikacijaDto> getAllNotifikacija() {
        List<Notifikacija> notifikacijaList = notifikacijaRepository.findAll();
        List<NotifikacijaDto> notifikacijaDtoList = new ArrayList<>();
        for(Notifikacija notifikacija : notifikacijaList) {
            notifikacijaDtoList.add(new NotifikacijaDto(notifikacija));
        }
        return notifikacijaDtoList;
    }
    public Optional<Notifikacija> getNotifikacijaById(Long id) {
        return notifikacijaRepository.findById(id);
    }

    public Notifikacija saveNotifikacija(Notifikacija notifikacija) {
        return notifikacijaRepository.save(notifikacija);
    }

    public void deleteNotifikacija(Long id) {
        notifikacijaRepository.deleteById(id);
    }

    public List<Notifikacija> getNotifikacijeKorisnika(Long korisnikId) {
       return notifikacijaRepository.findByKorisnikIdOrderByVrijemeKreiranjaDesc(korisnikId);
    }
}
