package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.RadnoVrijemeDto;
import com.example.ebeautycentar.entity.RadnoVrijeme;
import com.example.ebeautycentar.service.RadnoVrijemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/radno_vrijeme")
public class RadnoVrijemeController {
    private final RadnoVrijemeService radnoVrijemeService;

    @Autowired
    public RadnoVrijemeController(RadnoVrijemeService radnoVrijemeService) {
        this.radnoVrijemeService = radnoVrijemeService;
    }

    @GetMapping
    public List<RadnoVrijemeDto> getAllRadoVrijeme() {
        return radnoVrijemeService.getAllRadnoVrijeme();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RadnoVrijemeDto> getRadnoVrijemeById(@PathVariable Long id) {
        Optional<RadnoVrijeme> radnoVrijeme = radnoVrijemeService.getRadnoVrijemeById(id);
        if(radnoVrijeme.isPresent()) {
            RadnoVrijemeDto radnoVrijemeDto = new RadnoVrijemeDto(radnoVrijeme.get());
            return ResponseEntity.ok(radnoVrijemeDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
