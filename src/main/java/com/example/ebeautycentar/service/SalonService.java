package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.*;
import com.example.ebeautycentar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalonService {

    @Autowired
    private final SalonRepository salonRepository;

    @Autowired
    private final TipRepository tipRepository;

    @Autowired
    private final VlasnikSalonaRepository vlasnikSalonaRepository;

    @Autowired
    private final LokacijaRepository lokacijaRepository;

    @Autowired
    private final SlikaRepository slikaRepository;

    @Autowired
    public SalonService(SalonRepository salonRepository, TipRepository tipRepository, VlasnikSalonaRepository vlasnikSalonaRepository,LokacijaRepository lokacijaRepository, SlikaRepository slikaRepository) {
        this.salonRepository = salonRepository;
        this.tipRepository = tipRepository;
        this.vlasnikSalonaRepository=vlasnikSalonaRepository;
        this.lokacijaRepository=lokacijaRepository;
        this.slikaRepository=slikaRepository;
    }

    public List<SalonDto> getAllSalon() {
        List<Salon> salonList = salonRepository.findByStatus("A");
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
    }*/

    public List<SalonDto>getSaloniByGradAndUslugaAndNaziv(String grad,Integer usluga,String naziv)
    {
        String gradtmp;
        String nazivtmp;
        if (grad == null) {
            gradtmp = null;
        }else{
            gradtmp = "%"+grad+"%";
        }
        if (naziv == null) {
            nazivtmp = null;
        }else {
            nazivtmp = "%"+naziv+"%";
        }
        List<Salon>saloni=salonRepository.findByGradAndUslugaAndNaziv(gradtmp,usluga,nazivtmp,"A");
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public List<SalonDto>getPopularniSaloni()
    {
        List<Salon>saloni=salonRepository.findPopularni();
        return saloni.stream()
                .map(SalonDto::new)
                .collect(Collectors.toList());
    }

    public Salon nadjiPoNazivu(String naziv) {
        return salonRepository.findByNaziv(naziv)
                .orElseThrow(() -> new RuntimeException("Salon nije pronađen"));
    }

    public List<Salon> findByVlasnikSalonaId(Long vlasniSalonaId) {
        return salonRepository.findByVlasnikSalonaId(vlasniSalonaId);
    }

    public Salon addSalon(String naziv, String email, String brojTelefona,
                          Integer tipId, LocalDate datumOtvaranja, LocalDate datumZatvaranja, Long vlasnikId, String ulica,String broj,String grad,String drzava,String postanskiBroj,
                          MultipartFile slikaFile) {

        Tip tip = tipRepository.findById(tipId).orElseThrow(() -> new RuntimeException("Tip ne postoji"));
        VlasnikSalona vlasnik = vlasnikSalonaRepository.findById(vlasnikId)
                .orElseThrow(() -> new RuntimeException("Vlasnik ne postoji"));

        Lokacija lokacija = new Lokacija();
        lokacija.setUlica(ulica);
        lokacija.setBroj(broj);
        lokacija.setPostanskiBroj(postanskiBroj);
        lokacija.setGrad(grad);
        lokacija.setDrzava(drzava);
        lokacijaRepository.save(lokacija);

        Salon salon = new Salon();
        salon.setNaziv(naziv);
        salon.setEmail(email);
        salon.setBrojTelefona(brojTelefona);
        salon.setTip(tip);
        salon.setVlasnikSalona(vlasnik);
        salon.setLokacija(lokacija);
        salon.setDatumOtvaranja(datumOtvaranja);
        salon.setDatumZatvaranja(datumZatvaranja);
        salon.setStatus("A");
        salon.setProsjecnaOcjena(BigDecimal.ZERO);
        salonRepository.save(salon); // prvo spremamo salon da bismo imali ID

        if (slikaFile != null && !slikaFile.isEmpty()) {
            Slika slika = new Slika();
            slika.setNaziv(slikaFile.getOriginalFilename());
            slika.setSalon(salon);
            slika.setStatus("A");
            slika.setVrsta("G");
            slika.setUsluga(null); // jer je za salon
            try {
                slika.setSlika(slikaFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Greška pri čitanju slike");
            }
            slikaRepository.save(slika);
        }

        return salon;
    }
}
