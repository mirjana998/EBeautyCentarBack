package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.LokacijaDto;
import com.example.ebeautycentar.entity.Lokacija;
import com.example.ebeautycentar.service.LokacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/lokacija")
public class LokacijaController {

    private final LokacijaService lokacijaService;

    @Autowired
    public LokacijaController(LokacijaService lokacijaService) {
        this.lokacijaService = lokacijaService;
    }

    @GetMapping
    public List<LokacijaDto> getAllLokacija() {
        return lokacijaService.getAllLokacija();
    }

    @GetMapping("/distinct")
    public List<String> getAllDistinctLokacija() {
        return lokacijaService.getAllDistinctLokacija();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LokacijaDto> getLokacijaById(@PathVariable Long id) {
        Optional<Lokacija> lokacija = lokacijaService.getLokacijaById(id);
        if(lokacija.isPresent()) {
            LokacijaDto lokacijaDto = new LokacijaDto(lokacija.get());
            return ResponseEntity.ok(lokacijaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLokacija(@PathVariable Long id) {
        lokacijaService.deleteLokacija(id);
        return ResponseEntity.ok("OBRISAN!");
    }

    @GetMapping("/gradovi")
    public ResponseEntity<List<String>> getSviGradovi() {
        List<String> gradovi = lokacijaService.getSviGradovi();
        return ResponseEntity.ok(gradovi);
    }
}
