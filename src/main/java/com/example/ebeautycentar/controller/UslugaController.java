package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.UslugaDodajDTO;
import com.example.ebeautycentar.dto.UslugaDto;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.entity.Usluga;
import com.example.ebeautycentar.service.SalonService;
import com.example.ebeautycentar.service.SlikaService;
import com.example.ebeautycentar.service.UslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usluga")
public class UslugaController {

    private final UslugaService uslugaService;
    private final SlikaService slikaService;
    private final SalonService salonService;

    @Autowired
    public UslugaController(UslugaService uslugaService, SlikaService slikaService, SalonService salonService) {
        this.uslugaService = uslugaService;
        this.slikaService= slikaService;
        this.salonService=salonService;
    }

    @GetMapping
    public List<UslugaDto> getAllTip() {
        return uslugaService.getAllUsluga();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UslugaDto> getTipById(@PathVariable Long id) {
        Optional<Usluga> usluga = uslugaService.getUslugaById(id);
        if(usluga.isPresent()) {
            UslugaDto uslugaDto = new UslugaDto(usluga.get());
            return ResponseEntity.ok(uslugaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nazivi")
    public ResponseEntity<List<String>> getSveNaziveUsluga() {
        return ResponseEntity.ok(uslugaService.getSveUsluge());
    }

    @PostMapping("/dodaj")
    public ResponseEntity<String> dodajUslugu(
            @RequestParam("naziv") String naziv,
            @RequestParam("nazivSlike") String nazivSlike,
            @RequestParam("idSalona") Long idSalona) {
        try {
            Usluga novaUsluga = new Usluga();
            novaUsluga.setNaziv(naziv);
            novaUsluga.setStatus("A");
            Usluga sacuvanaUsluga = uslugaService.saveUsluga(novaUsluga);

            Salon salon = salonService.getSalonById(idSalona)
                    .orElseThrow(() -> new RuntimeException("Salon nije pronađen!"));

            Slika novaSlika = new Slika();
            novaSlika.setNaziv(nazivSlike);
            novaSlika.setVrsta("G");
            novaSlika.setStatus("A");
            novaSlika.setUsluga(sacuvanaUsluga);
            novaSlika.setSalon(salon);

            slikaService.saveSlika(novaSlika);

            return ResponseEntity.ok("Usluga i slika su uspješno dodane!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Greška prilikom dodavanja usluge!");
        }
    }
}
