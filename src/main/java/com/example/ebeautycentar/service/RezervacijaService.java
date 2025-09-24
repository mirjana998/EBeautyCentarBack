package com.example.ebeautycentar.service;


import com.example.ebeautycentar.dto.RezervacijaDto;
import com.example.ebeautycentar.dto.RezervacijaSalonDto;
import com.example.ebeautycentar.entity.Rezervacija;
import com.example.ebeautycentar.entity.Transakcija;
import com.example.ebeautycentar.repository.RezervacijaRepository;
import com.example.ebeautycentar.repository.TransakcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaService {

    @Autowired
    private final RezervacijaRepository rezervacijaRepository;
    private final TransakcijaRepository transakcijaRepository;

    @Autowired
    public RezervacijaService(RezervacijaRepository rezervacijaRepository,TransakcijaRepository transakcijaRepository) {
        this.rezervacijaRepository = rezervacijaRepository;
        this.transakcijaRepository=transakcijaRepository;
    }

    public List<Rezervacija> getAllRezervacija() {
       return rezervacijaRepository.findAll();
    }


    public Optional<Rezervacija> getRezervacijaById(Long id) {
        return rezervacijaRepository.findById(id);
    }


    public Rezervacija saveRezervacija(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    public void deleteRezevacija(Long id) {
        rezervacijaRepository.deleteById(id);
    }

    public List<Rezervacija> findByStatusAktivan(String status) {
        return rezervacijaRepository.findByStatus("A");
    }

    public List<RezervacijaSalonDto> getRezervacijaBySalonId(Long salonId)
    {
        List<Rezervacija>rezervacije=rezervacijaRepository.findBySalonId(salonId);

        return rezervacije.stream().map(r->{
            Long id = r.getId();
            String ime=r.getRegistrovaniKlijent().getKorisnik().getIme();
            String prezime=r.getRegistrovaniKlijent().getKorisnik().getPrezime();
            String nazivUsluge=r.getZaposleniSalonUsluga().getSalonUsluga().getUsluga().getNaziv();
            Double cijena=r.getZaposleniSalonUsluga().getSalonUsluga().getCijena();
            Optional<Transakcija> transakcijaOptional = transakcijaRepository.findByRezervacijaId(r.getId());
            String valuta = "";
            if(transakcijaOptional.isPresent()) {
                valuta = transakcijaOptional.get().getValuta();
            }
            Instant terminPocetka = r.getTerminPocetkaUsluge();
            Instant terminKraja = r.getTerminZavršetkaUsluge();
            String status = r.getStatus();
            Long korisnikId = r.getRegistrovaniKlijent().getId();
//            boolean placeno = transakcijaRepository.findByRezervacijaId(r.getId())
//                    .map(t -> "I".equalsIgnoreCase(t.getStatus()))
//                    .orElse(false);

            return new RezervacijaSalonDto(id,ime, prezime, nazivUsluge, cijena,valuta,status, terminPocetka, terminKraja,korisnikId);
        }).toList();
    }

    public List<Rezervacija> getRezervacijaByZaposleniId(Long zaposleniId) {
       return rezervacijaRepository.findByZaposleniId(zaposleniId, "I","P");
    }

    public List<Rezervacija> getRezervacijeKlijenta(Long id) {
        return rezervacijaRepository.findByRegistrovaniKlijentId(id);
    }

}
