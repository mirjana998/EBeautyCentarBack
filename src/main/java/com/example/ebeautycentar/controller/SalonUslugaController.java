package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.SalonUslugaDodajDto;
import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.dto.StatusUpdateDto;
import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.*;
import com.example.ebeautycentar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.*;
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
    private final SlikaService slikaService;
    private final SalonService salonService;
    private final UslugaService uslugaService;
    private final ZaposleniService zaposleniService;
    private final ZaposleniSalonUslugaService zaposleniSalonUslugaService;

    @Autowired
    public SalonUslugaController(SalonUslugaService salonUslugaService, SlikaService slikaService, SalonService salonService, UslugaService uslugaService, ZaposleniService zaposleniService, ZaposleniSalonUslugaService zaposleniSalonUslugaService) {
        this.salonUslugaService = salonUslugaService;
        this.slikaService = slikaService;
        this.salonService = salonService;
        this.uslugaService = uslugaService;
        this.zaposleniService = zaposleniService;
        this.zaposleniSalonUslugaService = zaposleniSalonUslugaService;
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
        List<SalonUsluga> salonUsluge = salonUslugaService.getSalonUslugaBySalonId(id);
        List<SalonUslugaDto> salonUslugaDtoList = new ArrayList<>();
        for (SalonUsluga s : salonUsluge) {
            SalonUslugaDto dto = new SalonUslugaDto(s);
            Optional<Slika> slikaOptional = slikaService.getSlikaByUslugaId(dto.getUslugaDto().getId());
            if (slikaOptional.isPresent()) {
                dto.setSlika(slikaOptional.get().getNaziv());
                salonUslugaDtoList.add(dto);
            }

        }
        return ResponseEntity.ok(salonUslugaDtoList);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<SalonUslugaDto> dodajSalonUslugu(@RequestBody SalonUslugaDodajDto salonUslugaDodajDto) throws IOException {
        Optional<Salon> salonOptional =salonService.getSalonById(salonUslugaDodajDto.getSalonaId());
        Optional<Usluga> uslugaOptional = uslugaService.getUslugaById(salonUslugaDodajDto.getUslugaId());
        SalonUsluga nova = new SalonUsluga();
        if(salonOptional.isPresent() && uslugaOptional.isPresent()) {
            nova.setUsluga(uslugaOptional.get());
            nova.setSalon(salonOptional.get());
            nova.setCijena(salonUslugaDodajDto.getCijena());
            nova.setOpis(salonUslugaDodajDto.getOpis());
            nova.setStatus("A");
            nova.setDatumPocetka(LocalDate.now());
            nova.setTrajanjeUsluge(LocalTime.parse(salonUslugaDodajDto.getTrajanjeUsluge()));
            SalonUsluga sacuvana = salonUslugaService.saveSalonUsluga(nova);
            //dodavanje svih zaposleni_salon_usluga za novu salon_uslugu
            List<Zaposleni> sviZaposleni = zaposleniService.findBySalon(salonOptional.get().getId());
            for(Zaposleni z : sviZaposleni) {
                ZaposleniSalonUsluga zsu = new ZaposleniSalonUsluga();
                zsu.setZaposleni(z);
                zsu.setSalonUsluga(sacuvana);
                zaposleniSalonUslugaService.saveZaposleniSalonUsluga(zsu);
                System.out.println("Sacuvana zsu: " + zsu);

            }
            return ResponseEntity.ok(new SalonUslugaDto(sacuvana));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<SalonUslugaDto> promijeniStatus(@PathVariable Long id) {
        Optional<SalonUsluga> salonUslugaOptional = salonUslugaService.getSalonUslugaById(id);
        if(salonUslugaOptional.isPresent()) {
            SalonUsluga stara = salonUslugaOptional.get();
            stara.setStatus(stara.getStatus().equals("A")?"N":"A");
            SalonUsluga sacuvana = salonUslugaService.saveSalonUsluga(stara);
            return ResponseEntity.ok(new SalonUslugaDto(sacuvana));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<SalonUslugaDto> azurirajSalonUslugu(@RequestBody SalonUslugaDto dto) {
        Optional<SalonUsluga> salonUslugaOptional = salonUslugaService.getSalonUslugaById(dto.getId());
        if(salonUslugaOptional.isPresent()) {
            SalonUsluga stara = salonUslugaOptional.get();
            stara.setOpis(dto.getOpis());
            stara.setCijena(dto.getCijena());
            stara.setTrajanjeUsluge(dto.getTrajanjeUsluge());
            SalonUsluga sacuvana = salonUslugaService.saveSalonUsluga(stara);
            return ResponseEntity.ok(new SalonUslugaDto(sacuvana));
        }
        return ResponseEntity.notFound().build();
    }

}
