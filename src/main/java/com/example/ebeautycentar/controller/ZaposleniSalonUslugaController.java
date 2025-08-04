package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.entity.ZaposleniSalonUsluga;
import com.example.ebeautycentar.dto.ZaposleniSalonUslugaDto;
import com.example.ebeautycentar.service.ZaposleniSalonUslugaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zaposleni_usluga")
public class ZaposleniSalonUslugaController {

    private final ZaposleniSalonUslugaService zaposleniSalonUslugaService;

    @Autowired
    public ZaposleniSalonUslugaController(ZaposleniSalonUslugaService zaposleniSalonUslugaService) {
        this.zaposleniSalonUslugaService = zaposleniSalonUslugaService;
    }

    @GetMapping
    public List<ZaposleniSalonUslugaDto> getAllZaposleniSalonUsluga() {
        return zaposleniSalonUslugaService.getAllZaposleniSalonUsluga();
    }

    @GetMapping("/{zaposleniId}/{salonUslugaId}")
    public ResponseEntity<ZaposleniSalonUslugaDto> getZaposleniSalonUslugaById(@PathVariable Long zaposleniId, @PathVariable Long salonUslugaId) {
        Optional<ZaposleniSalonUsluga> zaposleniSalonUsluga = zaposleniSalonUslugaService.getZaposleniSalonUslugaById(salonUslugaId, zaposleniId);
        if(zaposleniSalonUsluga.isPresent()) {
            ZaposleniSalonUslugaDto zaposleniSalonUslugaDto = new ZaposleniSalonUslugaDto(zaposleniSalonUsluga.get());
            return ResponseEntity.ok(zaposleniSalonUslugaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
