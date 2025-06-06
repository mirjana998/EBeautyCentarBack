package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.OcjenaKlijentaDto;
import com.example.ebeautycentar.entity.OcjenaKlijenta;
import com.example.ebeautycentar.service.OcjenaKlijentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ocjena_klijenta")
public class OcjenaKlijentaController {

    private final OcjenaKlijentaService ocjenaKlijentaService;

    @Autowired
    public OcjenaKlijentaController(OcjenaKlijentaService ocjenaKlijentaService) {
        this.ocjenaKlijentaService = ocjenaKlijentaService;
    }

    @GetMapping
    public List<OcjenaKlijentaDto> getAllOcjenaKlijenta() {
        return ocjenaKlijentaService.getAllOcjenaKlijenta();
    }

    @GetMapping("/{vlasnikId}/{klijentId}")
    public ResponseEntity<OcjenaKlijentaDto> getOcjenaKlijentaById(@PathVariable Long vlasnikId, @PathVariable Long klijentId) {
        Optional<OcjenaKlijenta> ocjenaKlijenta = ocjenaKlijentaService.getByVlasnikSalonaIdAndRegistrovaniKlijentId(vlasnikId, klijentId);
        if(ocjenaKlijenta.isPresent()) {
            OcjenaKlijentaDto ocjenaKlijentaDto = new OcjenaKlijentaDto(ocjenaKlijenta.get());
            return ResponseEntity.ok(ocjenaKlijentaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
