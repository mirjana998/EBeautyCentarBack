package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<KorisnikDto> getAllKorisnik() {
        return korisnikService.getAllKorisnik();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getKorisnikById(@PathVariable Long id) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikById(id);
        if(korisnik.isPresent()) {
            KorisnikDto korisnikDto = new KorisnikDto(korisnik.get());
            return ResponseEntity.ok(korisnikDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

