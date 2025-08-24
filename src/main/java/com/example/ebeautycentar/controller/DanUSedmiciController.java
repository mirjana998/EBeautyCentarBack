package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.DanUSedmiciDto;
import com.example.ebeautycentar.entity.DanUSedmici;
import com.example.ebeautycentar.service.DanUSedmiciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dan_u_sedmici")
public class DanUSedmiciController {

    private final DanUSedmiciService danUSedmiciService;

    @Autowired
    public DanUSedmiciController(DanUSedmiciService danUSedmiciService) {
        this.danUSedmiciService = danUSedmiciService;
    }

    @GetMapping
    public List<DanUSedmiciDto> getAllDanUSedmici() {
        return danUSedmiciService.getAllDanUSedmici();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DanUSedmiciDto> getDanUSedmiciById(@PathVariable int id) {
        Optional<DanUSedmici> danUSedmici = danUSedmiciService.getDanUSedmiciById(id);
        if(danUSedmici.isPresent()) {
            DanUSedmiciDto danUSedmiciDto = new DanUSedmiciDto(danUSedmici.get());
            return ResponseEntity.ok(danUSedmiciDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
