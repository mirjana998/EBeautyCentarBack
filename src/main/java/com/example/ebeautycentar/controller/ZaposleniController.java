package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.VlasnikSalona;
import com.example.ebeautycentar.entity.Zaposleni;
import com.example.ebeautycentar.service.SalonService;
import com.example.ebeautycentar.service.VlasnikSalonaService;
import com.example.ebeautycentar.service.ZaposleniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

    private final ZaposleniService zaposleniService;
    private final VlasnikSalonaService vlasnikSalonaService;
    private final SalonService salonService;

    @Autowired
    public ZaposleniController(ZaposleniService zaposleniService, VlasnikSalonaService vlasnikSalonaService, SalonService salonService) {
        this.zaposleniService = zaposleniService;
        this.vlasnikSalonaService = vlasnikSalonaService;
        this.salonService = salonService;
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
        List<ZaposleniDto> zaposleniDtoList = zaposleniService.findBySalon(id);
        if(zaposleniDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(zaposleniDtoList);
    }

    @PostMapping("/dodaj")
    public ResponseEntity<ZaposleniDto> dodajZaposlenog(@RequestBody ZaposleniDto zaposleniDto) {
        Zaposleni zaposleni = new Zaposleni();
        zaposleni.setIme(zaposleniDto.getIme());
        zaposleni.setPrezime(zaposleniDto.getPrezime());
        zaposleni.setAktivan("A");

        Optional<VlasnikSalona> vlasnikSalonaOptional = vlasnikSalonaService.getVlasnikSalonaById(zaposleniDto.getVlasnikSalonaId());
        Optional<Salon> salonOptional = salonService.getSalonById(zaposleniDto.getSalonId());
        if(vlasnikSalonaOptional.isPresent() && salonOptional.isPresent()) {
            zaposleni.setVlasnikSalona(vlasnikSalonaOptional.get());
            zaposleni.setSalon(salonOptional.get());

            Zaposleni noviZaposleni = zaposleniService.saveZaposleni(zaposleni);
            return ResponseEntity.ok(new ZaposleniDto(noviZaposleni));
        }
        else
            return ResponseEntity.notFound().build();

    }
}
