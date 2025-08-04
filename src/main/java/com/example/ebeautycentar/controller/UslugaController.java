package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.UslugaDto;
import com.example.ebeautycentar.entity.Usluga;
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
}
