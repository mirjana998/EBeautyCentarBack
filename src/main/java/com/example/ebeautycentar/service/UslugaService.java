package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.UslugaDto;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.entity.Usluga;
import com.example.ebeautycentar.repository.SalonRepository;
import com.example.ebeautycentar.repository.SlikaRepository;
import com.example.ebeautycentar.repository.UslugaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UslugaService {

    @Autowired
    private final UslugaRepository uslugaRepository;

    @Autowired
    private final SalonRepository salonRepository;
    @Autowired
    private SlikaRepository slikaRepository;

    @Autowired
    public UslugaService(UslugaRepository uslugaRepository, SalonRepository salonRepository, SlikaRepository slikaRepository) {
        this.uslugaRepository = uslugaRepository;
        this.salonRepository = salonRepository;
        this.slikaRepository = slikaRepository;
    }

    public List<UslugaDto> getAllUsluga() {
        List<Usluga> uslugaList = uslugaRepository.findAll();
        List<UslugaDto> uslugaDtoList = new ArrayList<>();
        for (Usluga usluga : uslugaList) {
            uslugaDtoList.add(new UslugaDto(usluga));
        }
        return uslugaDtoList;
    }


    public Optional<Usluga> getUslugaById(Long id) {
        return uslugaRepository.findById(id);
    }


    public Usluga saveUsluga(Usluga usluga) {
        return uslugaRepository.save(usluga);
    }

    public void deleteUsluga(Long id) {
        uslugaRepository.deleteById(id);
    }

    public List<Usluga> findByStatusAktivan(String status) {
        return uslugaRepository.findByStatus("A");
    }

    public List<String> getSveUsluge() {
        return uslugaRepository.findDistinctNaziv();
    }


    public Usluga dodajUsluguSaSlikom(String naziv, Long salonId, MultipartFile file) throws IOException {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new RuntimeException("Salon ne postoji"));

        Usluga usluga = new Usluga();
        usluga.setNaziv(naziv);
        usluga.setStatus("A");
        usluga = uslugaRepository.save(usluga);

        Slika slika = new Slika();
        slika.setNaziv(file.getOriginalFilename());
        slika.setSlika(file.getBytes());
        slika.setStatus("A");
        slika.setVrsta("G");
        slika.setUsluga(usluga);
        slika.setSalon(salon);
        slikaRepository.save(slika);

        return usluga;
    }
}
