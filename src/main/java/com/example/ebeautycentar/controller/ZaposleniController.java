package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.*;
import com.example.ebeautycentar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

    private final ZaposleniService zaposleniService;
    private final VlasnikSalonaService vlasnikSalonaService;
    private final SalonService salonService;
    private final ZaposleniSalonUslugaService zaposleniSalonUslugaService;
    private final SalonUslugaService salonUslugaService;

    @Autowired
    public ZaposleniController(ZaposleniService zaposleniService, VlasnikSalonaService vlasnikSalonaService, SalonService salonService, ZaposleniSalonUslugaService zaposleniSalonUslugaService, SalonUslugaService salonUslugaService) {
        this.zaposleniService = zaposleniService;
        this.vlasnikSalonaService = vlasnikSalonaService;
        this.salonService = salonService;
        this.zaposleniSalonUslugaService = zaposleniSalonUslugaService;
        this.salonUslugaService = salonUslugaService;
    }

    @GetMapping
    public List<ZaposleniDto> getAllZaposleni() {
        return zaposleniService.getAllZaposleni();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZaposleniDto> getZaposleniById(@PathVariable Long id) {
        Optional<Zaposleni> zaposleni = zaposleniService.getZaposleniById(id);
        if(zaposleni.isPresent()) {
            ZaposleniDto zaposleniDto = new ZaposleniDto(zaposleni.get());
            return ResponseEntity.ok(zaposleniDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/salon")
    public ResponseEntity<List<ZaposleniDto>> getAllZaposleniBySalon(@RequestParam Long id) {
        List<Zaposleni> zaposleniList = zaposleniService.findBySalon(id);
        List<ZaposleniDto> zaposleniDtoList = new ArrayList<>();
        for(Zaposleni zaposleni : zaposleniList) {
            zaposleniDtoList.add(new ZaposleniDto(zaposleni));
        }
        return ResponseEntity.ok(zaposleniDtoList);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<ZaposleniDto> dodajZaposlenog(@RequestBody ZaposleniDto zaposleniDto) {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setIme(zaposleniDto.getIme());
        zaposleni.setPrezime(zaposleniDto.getPrezime());
        zaposleni.setAktivan("A");

        Optional<Salon> salonOptional = salonService.getSalonById(zaposleniDto.getSalonId());
        if(salonOptional.isPresent()) {
                zaposleni.setVlasnikSalona(salonOptional.get().getVlasnikSalona());
                zaposleni.setSalon(salonOptional.get());

                Zaposleni noviZaposleni = zaposleniService.saveZaposleni(zaposleni);
                List<SalonUsluga> salonUslugaList = salonUslugaService.getSalonUslugaBySalonId(salonOptional.get().getId());
                for (SalonUsluga su : salonUslugaList) {
                    ZaposleniSalonUsluga zsu = new ZaposleniSalonUsluga();
                    zsu.setSalonUsluga(su);
                    zsu.setZaposleni(noviZaposleni);
                    zaposleniSalonUslugaService.saveZaposleniSalonUsluga(zsu);
                }
                return ResponseEntity.ok(new ZaposleniDto(noviZaposleni));
            }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<ZaposleniDto> promijeniStatus(@PathVariable Long id) {
        Optional<Zaposleni> zaposleniOptional = zaposleniService.getZaposleniById(id);
        if(zaposleniOptional.isPresent()) {
            Zaposleni stari = zaposleniOptional.get();
            stari.setAktivan(stari.getAktivan().equals("A")?"N":"A");
            Zaposleni sacuvan = zaposleniService.saveZaposleni(stari);
            return ResponseEntity.ok(new ZaposleniDto(sacuvan));
        }
        return ResponseEntity.notFound().build();
    }
}
