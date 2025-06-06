package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.TipDto;
import com.example.ebeautycentar.entity.Tip;
import com.example.ebeautycentar.service.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tip")
public class TipController {

    private final TipService tipService;

    @Autowired
    public TipController(TipService tipService) {
        this.tipService = tipService;
    }

    @GetMapping
    public List<TipDto> getAllTip() {
        return tipService.getAllTip();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipDto> getTipById(@PathVariable int id) {
        Optional<Tip> tip = tipService.getTipById(id);
        if(tip.isPresent()) {
            TipDto tipDto = new TipDto(tip.get());
            return ResponseEntity.ok(tipDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
