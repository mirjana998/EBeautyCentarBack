package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.SlikaDto;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.repository.SlikaRepository;
import com.example.ebeautycentar.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/slika")
public class SlikaController {

    private final SlikaService slikaService;

    private final SlikaRepository slikaRepository;

    @Autowired
    public SlikaController(SlikaService slikaService,SlikaRepository slikaRepository) {
        this.slikaService = slikaService;
        this.slikaRepository=slikaRepository;
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

    //end point kako bi se ucitala slika na frontu za datu uslugu znaci samo da se prikaze usluga i njena slika
    @GetMapping("/usluga/{uslugaId}")
    public ResponseEntity<?> getSlikaByUsluga(@PathVariable Long uslugaId) {
        Optional<Slika> slikaOpt = slikaRepository.findByUslugaId(uslugaId);

        if (slikaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("poruka", "Slika za ovu uslugu ne postoji"));
        }

        Slika slika = slikaOpt.get();
        String base64 = Base64.getEncoder().encodeToString(slika.getSlika());

        return ResponseEntity.ok(Map.of(
                "naziv", slika.getNaziv(),
                "slikaBase64", base64
        ));
    }


}
