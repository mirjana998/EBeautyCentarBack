package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.OcjenaPruzeneUslugeDto;
import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import com.example.ebeautycentar.service.OcjenaPruzeneUslugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ocjena_usluge")
public class OcjenaPruzeneUslugeController {

    private final OcjenaPruzeneUslugeService ocjenaPruzeneUslugeService;

    @Autowired
    public OcjenaPruzeneUslugeController(OcjenaPruzeneUslugeService ocjenaPruzeneUslugeService) {
        this.ocjenaPruzeneUslugeService = ocjenaPruzeneUslugeService;
    }

    @GetMapping
    public List<OcjenaPruzeneUslugeDto> getAllOcjenaPruzeneUsluge() {
        return ocjenaPruzeneUslugeService.getAllOcjenaPruženeUsluge();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcjenaPruzeneUslugeDto> getOcjenaPruzeneUslugeById(@PathVariable Long id) {
        Optional<OcjenaPruženeUsluge> ocjenaPruzeneUsluge = ocjenaPruzeneUslugeService.getOcjenaPruženeUslugeById(id);
        if(ocjenaPruzeneUsluge.isPresent()) {
            OcjenaPruzeneUslugeDto ocjenaPruzeneUslugeDto = new OcjenaPruzeneUslugeDto(ocjenaPruzeneUsluge.get());
            return ResponseEntity.ok(ocjenaPruzeneUslugeDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/salon")
    public ResponseEntity<List<OcjenaPruzeneUslugeDto>> getOcjenaPruzeneUslugeBySalonId(@RequestParam Long id) {
       List<OcjenaPruzeneUslugeDto> ocjene = ocjenaPruzeneUslugeService.getAllOcjenaPruzeneUslugeBySalonId(id);
       if(ocjene.isEmpty()) {
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(ocjene);
    }



}
