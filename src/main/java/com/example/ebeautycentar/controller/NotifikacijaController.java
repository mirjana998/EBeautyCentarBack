package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.NotifikacijaDto;
import com.example.ebeautycentar.entity.Notifikacija;
import com.example.ebeautycentar.service.NotifikacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/notifikacija")
public class NotifikacijaController {

    private final NotifikacijaService notifikacijaService;

    @Autowired
    public  NotifikacijaController(NotifikacijaService notifikacijaService) {
        this.notifikacijaService = notifikacijaService;
    }

    @GetMapping
    public List<NotifikacijaDto> getAllNotifikacija() {
        return notifikacijaService.getAllNotifikacija();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotifikacijaDto> getNotifikacijaById(@PathVariable Long id) {
        Optional<Notifikacija> notifikacija = notifikacijaService.getNotifikacijaById(id);
        if(notifikacija.isPresent()) {
            NotifikacijaDto notifikacijaDto = new NotifikacijaDto(notifikacija.get());
            return ResponseEntity.ok(notifikacijaDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotifikacija(@PathVariable Long id) {
        notifikacijaService.deleteNotifikacija(id);
        return ResponseEntity.ok("OBRISAN!");
    }


}
