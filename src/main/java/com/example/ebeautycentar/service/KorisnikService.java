package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    public List<KorisnikDto> getAllKorisnik() {
        List<Korisnik> korisnikList = korisnikRepository.findAll();
        List<KorisnikDto> korisnikDtoList = new ArrayList<>();
        for(Korisnik korisnik : korisnikList) {
            korisnikDtoList.add(new KorisnikDto(korisnik));
        }
        return korisnikDtoList;
    }


    public Optional<Korisnik> getKorisnikById(Long id) {
        return korisnikRepository.findById(id);
    }



    public Korisnik saveKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void deleteKorisnik(Long id) {
        korisnikRepository.deleteById(id);
    }

    public List<Korisnik> findByStatusAktivan(String status) {
        return korisnikRepository.findByStatus("A");
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


