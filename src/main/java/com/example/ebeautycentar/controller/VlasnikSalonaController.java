package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.VlasnikSalonaDto;
import com.example.ebeautycentar.dto.VlasnikSalonaLoginDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.VlasnikSalona;
import com.example.ebeautycentar.service.KorisnikService;
import com.example.ebeautycentar.service.VlasnikSalonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vlasnik_salona")
public class VlasnikSalonaController {

    @Autowired
    private final VlasnikSalonaService vlasnikSalonaService;
    private final KorisnikService korisnikService;

    @Autowired
    public VlasnikSalonaController(VlasnikSalonaService vlasnikSalonaService, KorisnikService korisnikService) {
        this.vlasnikSalonaService = vlasnikSalonaService;
        this.korisnikService = korisnikService;
    }

    @PostMapping("/login")
    public ResponseEntity<VlasnikSalonaDto> prijavaVlasnikaSalona(@RequestBody VlasnikSalonaLoginDto dto) {
        Optional<Korisnik> korisnikOptional = korisnikService.findByKorisnickoIme(dto.getUsername());
        if(korisnikOptional.isPresent()) {
            Optional<VlasnikSalona> vlasnikSalonaOptional = vlasnikSalonaService.findById(korisnikOptional.get().getId());
            if(vlasnikSalonaOptional.isPresent()) {
                return ResponseEntity.ok(new VlasnikSalonaDto(vlasnikSalonaOptional.get()));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{vlasnikId}/subscription")
    public ResponseEntity<String> updateSubscription(
            @PathVariable Long vlasnikId,
            @RequestBody Map<String, String> body) {

        String subscriptionId = body.get("subscriptionId");
        boolean updated = vlasnikSalonaService.updateSubscriptionId(vlasnikId, subscriptionId);

        if (updated) {
            return ResponseEntity.ok("Subscription ID updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{vlasnikId}")
    public ResponseEntity<Map<String, String>> getVlasnik(@PathVariable Long vlasnikId) {
        Optional<VlasnikSalona> optionalVlasnik = vlasnikSalonaService.findById(vlasnikId);
        if (optionalVlasnik.isPresent()) {
            VlasnikSalona vlasnik = optionalVlasnik.get();
            return ResponseEntity.ok(Map.of(
                    "id", vlasnik.getId().toString(),
                    "subscriptionId", vlasnik.getSubscriptionId() == null ? "" : vlasnik.getSubscriptionId()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
