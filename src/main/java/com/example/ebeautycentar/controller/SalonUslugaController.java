package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.SalonUslugaDto;
import com.example.ebeautycentar.entity.SalonUsluga;
import com.example.ebeautycentar.service.SalonUslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salon_usluga")
public class SalonUslugaController {

    private final SalonUslugaService salonUslugaService;

    @Autowired
    public SalonUslugaController(SalonUslugaService salonUslugaService) {
        this.salonUslugaService = salonUslugaService;
    }

    @GetMapping
    public List<SalonUslugaDto> getAllKorisnik() {
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
}
