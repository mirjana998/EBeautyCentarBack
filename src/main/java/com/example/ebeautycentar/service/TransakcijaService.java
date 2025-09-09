package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.TransakcijaDto;
import com.example.ebeautycentar.entity.Transakcija;
import com.example.ebeautycentar.repository.TransakcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransakcijaService {

    @Autowired
    private final TransakcijaRepository transakcijaRepository;

    @Autowired
    public TransakcijaService(TransakcijaRepository transakcijaRepository) {
        this.transakcijaRepository = transakcijaRepository;
    }

    public TransakcijaDto dodajTransakciju(Transakcija transakcija) {
        Transakcija t = this.transakcijaRepository.save(transakcija);
        return new TransakcijaDto(t);
    }
    public Optional<Transakcija> getTransakcijaByRezervacijaId(Long rezervacijaId) {
        return transakcijaRepository.findByRezervacijaId(rezervacijaId);
    }

}
