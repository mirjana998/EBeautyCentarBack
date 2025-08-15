package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.SalonUslugaDodajDto;
import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.entity.RadnoVrijeme;
import com.example.ebeautycentar.entity.SalonUsluga;
import com.example.ebeautycentar.service.RadnoVrijemeService;
import com.example.ebeautycentar.service.SalonUslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/salon_usluga")
public class SalonUslugaController {

    private final SalonUslugaService salonUslugaService;
    private final RadnoVrijemeService radnoVrijemeService;

    @Autowired
    public SalonUslugaController(SalonUslugaService salonUslugaService, RadnoVrijemeService radnoVrijemeService) {
        this.salonUslugaService = salonUslugaService;
        this.radnoVrijemeService = radnoVrijemeService;
    }

    @GetMapping
    public List<SalonUslugaDto> getAllSalonUsluga() {
        return salonUslugaService.getAllSalonUsluga();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonUslugaDto> getSalonUslugaById(@PathVariable Long id) {
        Optional<SalonUsluga> salonUsluga = salonUslugaService.getSalonUslugaById(id);
        if(salonUsluga.isPresent()) {
            SalonUslugaDto salonUslugaDto = new SalonUslugaDto(salonUsluga.get());
            return ResponseEntity.ok(salonUslugaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/salon")
    public ResponseEntity<List<SalonUslugaDto>> getSalonUslugaBySalonId(@RequestParam Long id) {
       List<SalonUslugaDto> salonUsluge = salonUslugaService.getSalonUslugaBySalonId(id);
        if(salonUsluge.isEmpty()) {
            return ResponseEntity.noContent().build();
        }return ResponseEntity.ok(salonUsluge);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<SalonUslugaDto> dodajSalonUslugu(@RequestParam String nazivSalona,
                                                           @RequestParam String nazivUsluge,
                                                           @RequestParam String trajanje_usluge,
                                                           @RequestParam Double cijena,
                                                           @RequestParam(required = false) String opis,
                                                           @RequestParam(required = false) MultipartFile slika) throws IOException {

        LocalTime trajanje = LocalTime.parse(trajanje_usluge);

        SalonUslugaDodajDto dto = new SalonUslugaDodajDto();
        dto.setNazivSalona(nazivSalona);
        dto.setNazivUsluge(nazivUsluge);
        dto.setTrajanje_usluge(trajanje);
        dto.setCijena(cijena);
        dto.setOpis(opis);
        SalonUslugaDto nova = salonUslugaService.kreirajSalonUslugu(dto,slika);
        return ResponseEntity.ok(nova);
    }

}
