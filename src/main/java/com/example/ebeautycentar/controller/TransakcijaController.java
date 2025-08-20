package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.TransakcijaDto;
import com.example.ebeautycentar.dto.TransakcijaStripeDto;
import com.example.ebeautycentar.entity.Rezervacija;
import com.example.ebeautycentar.entity.Transakcija;
import com.example.ebeautycentar.service.RezervacijaService;
import com.example.ebeautycentar.service.TransakcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/transakcija")
public class TransakcijaController {

    public final TransakcijaService transakcijaService;
    public final RezervacijaService rezervacijaService;

    @Autowired
    public TransakcijaController(TransakcijaService transakcijaService, RezervacijaService rezervacijaService) {
        this.transakcijaService = transakcijaService;
        this.rezervacijaService = rezervacijaService;
    }

    @PostMapping("/dodaj")
    public ResponseEntity<TransakcijaDto> dodajTransakciju(@RequestBody TransakcijaStripeDto transakcijaDto) {
        System.out.println("Primljeno: " + transakcijaDto);

        Optional<Rezervacija> rezervacijaOptional = rezervacijaService.getRezervacijaById(transakcijaDto.getRezervacijaId());
        if (rezervacijaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Transakcija transakcija = new Transakcija();
        transakcija.setValuta(transakcijaDto.getValuta());
        transakcija.setIznos(transakcijaDto.getUkupanIznos());
        transakcija.setDatumTransakcije(transakcijaDto.getDatumTransakcije());
        transakcija.setStatus(transakcijaDto.getStatus());
        transakcija.setRezervacija(rezervacijaOptional.get());

        return ResponseEntity.ok(transakcijaService.dodajTransakciju(transakcija));
    }


}
