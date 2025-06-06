package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salon")
public class SalonController {

    private final SalonService salonService;

    @Autowired
    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping
    public List<SalonDto> getAllSalon() {
        return salonService.getAllSalon();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long id) {
        Optional<Salon> salon = salonService.getSalonById(id);
        if(salon.isPresent()) {
            SalonDto salonDto = new SalonDto(salon.get());
            return ResponseEntity.ok(salonDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
        return ResponseEntity.ok("OBRISAN!");
    }
}
