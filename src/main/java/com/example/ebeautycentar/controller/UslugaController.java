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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/usluga")
public class UslugaController {

    private final UslugaService uslugaService;


    @Autowired
    public UslugaController(UslugaService uslugaService) {
        this.uslugaService = uslugaService;
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
    public ResponseEntity<?> dodajUslugu(
            @RequestParam Long salonId,
            @RequestParam String naziv,
            @RequestParam("slika") MultipartFile file) {

        try {
            Usluga usluga = uslugaService.dodajUsluguSaSlikom(naziv,salonId,file);
            return ResponseEntity.ok(Map.of(
                    "uslugaId", usluga.getId(),
                    "naziv", usluga.getNaziv(),
                    "poruka", "Usluga dodana sa slikom: " + file.getOriginalFilename()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("poruka", "Neuspjelo dodavanje usluge", "greska", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsluga(@PathVariable Long id) {
        uslugaService.deleteUsluga(id);
        return ResponseEntity.ok("Obrisana usluga!");
    }
}
