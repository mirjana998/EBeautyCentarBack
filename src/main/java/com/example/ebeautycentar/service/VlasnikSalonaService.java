package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.VlasnikSalonaDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Uloga;
import com.example.ebeautycentar.entity.VlasnikSalona;
import com.example.ebeautycentar.repository.KorisnikRepository;
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
    private final KorisnikRepository korisnikRepository;
    @Autowired
    private final UlogaService ulogaService;

    @Autowired
    public VlasnikSalonaService(VlasnikSalonaRepository vlasnikSalonaRepository,KorisnikRepository korisnikRepository, UlogaService ulogaService) {
        this.vlasnikSalonaRepository = vlasnikSalonaRepository;
        this.korisnikRepository = korisnikRepository;
        this.ulogaService = ulogaService;
    }

    public List<VlasnikSalonaDto> getAllKorisnik() {
        List<VlasnikSalona> vlasnikSalonaList = vlasnikSalonaRepository.findAll();
        List<VlasnikSalonaDto> vlasnikSalonaDtoList = new ArrayList<>();
        for (VlasnikSalona vlasnikSalona : vlasnikSalonaList) {
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

    public boolean updateSubscriptionId(Long id, String subscriptionId) {
        Optional<VlasnikSalona> vlasnikOptional = vlasnikSalonaRepository.findById(id);
        if (vlasnikOptional.isPresent()) {
            VlasnikSalona vlasnik = vlasnikOptional.get();
            vlasnik.setSubscriptionId(subscriptionId);
            vlasnikSalonaRepository.save(vlasnik);
            return true;
        }
        return false;
    }

    public VlasnikSalonaDto kreirajVlasnika(KorisnikDto korisnikDto) {

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(korisnikDto.getIme());
        korisnik.setPrezime(korisnikDto.getPrezime());
        korisnik.setBrojTelefona(korisnikDto.getBrojTelefona());
        korisnik.setEmail(korisnikDto.getEmail());
        korisnik.setKorisnickoIme(korisnikDto.getKorisnickoIme());
        korisnik.setLozinka(korisnikDto.getLozinka());
        korisnik.setStatus("A");
        korisnik.setClerkUserId(null);
        Optional<Uloga> uloga = ulogaService.getUlogaById(2);
        korisnik.setUloga(uloga.get());
        korisnik = korisnikRepository.save(korisnik);

        VlasnikSalona vlasnik = new VlasnikSalona();
        vlasnik.setKorisnik(korisnik);
        vlasnik.setSubscriptionId(null);
        vlasnik = vlasnikSalonaRepository.save(vlasnik);

        return new VlasnikSalonaDto(vlasnik);
    }

    public List<VlasnikSalona> getAllVlasnici() {
        return vlasnikSalonaRepository.findAll();
    }

    public VlasnikSalona updateVlasnik(Long vlasnikId, String ime, String prezime, String brojTelefona, String email) {
        VlasnikSalona vlasnik = vlasnikSalonaRepository.findById(vlasnikId)
                .orElseThrow(() -> new RuntimeException("Vlasnik salona ne postoji"));

        Korisnik korisnik = vlasnik.getKorisnik();
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setBrojTelefona(brojTelefona);
        korisnik.setEmail(email);

        return vlasnikSalonaRepository.save(vlasnik);
    }

}
