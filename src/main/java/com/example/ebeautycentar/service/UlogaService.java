package com.example.ebeautycentar.service;

import com.example.ebeautycentar.entity.Uloga;
import com.example.ebeautycentar.repository.UlogaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UlogaService {

    @Autowired
    private final UlogaRepository ulogaRepository;

    @Autowired
    public UlogaService(UlogaRepository ulogaRepository) {
        this.ulogaRepository = ulogaRepository;
    }


    public Optional<Uloga> getUlogaById(Integer id) {
        return ulogaRepository.findById(id);
    }

    public Uloga saveUloga(Uloga uloga) {
        return ulogaRepository.save(uloga);
    }

    public void deleteUloga(Integer id) {
        ulogaRepository.deleteById(id);
    }

}
