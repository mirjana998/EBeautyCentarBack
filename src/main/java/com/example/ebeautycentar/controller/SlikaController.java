package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.SlikaDto;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/slika")
public class SlikaController {

    private final SlikaService slikaService;

    @Autowired
    public SlikaController(SlikaService slikaService) {
        this.slikaService = slikaService;
    }

    @GetMapping
    public List<SlikaDto> getAllSlika() {
        return slikaService.getAllSlika();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlikaDto> getKorisnikById(@PathVariable Long id) {
        Optional<Slika> slika = slikaService.getSlikaById(id);
        if(slika.isPresent()) {
            SlikaDto slikaDto = new SlikaDto(slika.get());
            return ResponseEntity.ok(slikaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
