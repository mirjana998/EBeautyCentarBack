package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.NovostDto;
import com.example.ebeautycentar.entity.Novost;
import com.example.ebeautycentar.service.NovostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/novost")
public class NovostController {

    private final NovostService novostService;

    @Autowired
    public NovostController(NovostService novostService) {
        this.novostService = novostService;
    }

    @GetMapping
    public List<NovostDto> getAllNovost() {
        return novostService.getAllNovost();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNovost(@PathVariable Long id) {
        novostService.deleteNovost(id);
        return ResponseEntity.ok("OBRISAN!");
    }

    @GetMapping("/salon")
    public ResponseEntity<List<NovostDto>> getSalonNovosti(@RequestParam Long id) {
        List<NovostDto> novosti = novostService.findBySalonId(id);
        if (novosti.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(novosti);
    }
}
