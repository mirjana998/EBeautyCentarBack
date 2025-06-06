package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.Zaposleni;
import com.example.ebeautycentar.service.ZaposleniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

    private final ZaposleniService zaposleniService;

    @Autowired
    public ZaposleniController(ZaposleniService zaposleniService) {
        this.zaposleniService = zaposleniService;
    }

    @GetMapping
    public List<ZaposleniDto> getAllZaposleni() {
        return zaposleniService.getAllZaposleni();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZaposleniDto> getZaposleniById(@PathVariable Long id) {
        Optional<Zaposleni> zaposleni = zaposleniService.getZaposleniById(id);
        if(zaposleni.isPresent()) {
            ZaposleniDto zaposleniDto = new ZaposleniDto(zaposleni.get());
            return ResponseEntity.ok(zaposleniDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
