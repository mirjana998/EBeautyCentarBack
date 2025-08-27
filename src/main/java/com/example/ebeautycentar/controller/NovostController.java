package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.NovostDto;
import com.example.ebeautycentar.dto.RadnoVrijemeDto;
import com.example.ebeautycentar.entity.Novost;
import com.example.ebeautycentar.entity.RadnoVrijeme;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.service.NovostService;
import com.example.ebeautycentar.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/novost")
public class NovostController {

    private final NovostService novostService;
    private final SalonService salonService;

    @Autowired
    public NovostController(NovostService novostService, SalonService salonService) {
        this.novostService = novostService;
        this.salonService = salonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovostDto> getNovostById(@PathVariable Long id) {
        Optional<Novost> novost = novostService.getNovostById(id);
        if(novost.isPresent()) {
            NovostDto novostDto = new NovostDto(novost.get());
            return ResponseEntity.ok(novostDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/salon")
    public ResponseEntity<List<NovostDto>> getSalonNovosti(@RequestParam Long id) {
        List<NovostDto> novosti = novostService.findBySalonId(id);
        if (novosti.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(novosti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NovostDto> updateNovost(@RequestBody NovostDto dto) {
        Optional<Novost> novostDtoOptional = novostService.getNovostById(dto.getId());
        if(novostDtoOptional.isPresent()) {
            novostDtoOptional.get().setSadrzaj(dto.getSadrzaj());
            novostDtoOptional.get().setStatus(dto.getStatus());
            novostDtoOptional.get().setNaslov(dto.getNaslov());

            Novost sacuvana = novostService.saveNovost(novostDtoOptional.get());
            return ResponseEntity.ok(new NovostDto(sacuvana));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/nova")
    public ResponseEntity<NovostDto> dodajNovuNovost(@RequestBody NovostDto dto) {
        Novost novost = new Novost();
        novost.setNaslov(dto.getNaslov());
        novost.setSadrzaj(dto.getSadrzaj());
        novost.setStatus("A");
        novost.setVrijemeKreiranja(Instant.now());

        Optional<Salon> salonOptional = salonService.getSalonById(dto.getSalonId());
        if(salonOptional.isPresent()) {
            novost.setSalon(salonOptional.get());
            novost.setVlasnikSalona(salonOptional.get().getVlasnikSalona());
            Novost sacuvana = novostService.saveNovost(novost);
            return ResponseEntity.ok(new NovostDto(sacuvana));
        }
        return ResponseEntity.badRequest().build();
    }
}
