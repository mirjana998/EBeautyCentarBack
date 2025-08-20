package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.RegistrovaniKlijentDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import com.example.ebeautycentar.repository.KorisnikRepository;
import com.example.ebeautycentar.repository.RegistrovaniKlijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrovaniKlijentService {

    @Autowired
    private final RegistrovaniKlijentRepository registrovsniKlijentRespository;
    private final KorisnikRepository korisnikRepository;
    @Autowired
    public RegistrovaniKlijentService(
            RegistrovaniKlijentRepository registrovaniKlijentRepository,
            KorisnikRepository korisnikRepository
    ) {
        this.registrovsniKlijentRespository = registrovaniKlijentRepository;
        this.korisnikRepository = korisnikRepository;
    }

    public List<RegistrovaniKlijentDto> getAllRegistrovaniKlijent() {
        List<RegistrovaniKlijent> registrovaniKlijentList = registrovsniKlijentRespository.findAll();
        List<RegistrovaniKlijentDto> registrovaniKlijentDtoList = new ArrayList<>();
        for(RegistrovaniKlijent registrovaniKlijent : registrovaniKlijentList) {
            registrovaniKlijentDtoList.add(new RegistrovaniKlijentDto(registrovaniKlijent));
        }
        return registrovaniKlijentDtoList;
    }


    public Optional<RegistrovaniKlijent> getRegistrovaniKlijentById(Long id) {
        return registrovsniKlijentRespository.findById(id);
    }


    public RegistrovaniKlijent saveRegistrovaniKlijent(RegistrovaniKlijent registrovaniKlijent) {
        return registrovsniKlijentRespository.save(registrovaniKlijent);
    }

    public void deleteNotifikacija(Long id) {
        registrovsniKlijentRespository.deleteById(id);
    }


    public Optional<RegistrovaniKlijent> getByKorisnikId(Long korisnikId) {
        return registrovsniKlijentRespository.findByKorisnikId(korisnikId);
    }

    public Optional<RegistrovaniKlijent> getByClerkUserId(String clerkUserId) {
        Optional<Korisnik> korisnikOpt = korisnikRepository.findByClerkUserId(clerkUserId);
        if (korisnikOpt.isPresent()) {
            return registrovsniKlijentRespository.findByKorisnikId(korisnikOpt.get().getId());
        }
        return Optional.empty();
    }

}
