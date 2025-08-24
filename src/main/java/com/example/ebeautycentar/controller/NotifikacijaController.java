package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.NotifikacijaDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Notifikacija;
import com.example.ebeautycentar.service.KorisnikService;
import com.example.ebeautycentar.service.NotifikacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notifikacija")
public class NotifikacijaController {

    private final NotifikacijaService notifikacijaService;
    private final KorisnikService korisnikService;

    @Autowired
    public  NotifikacijaController(NotifikacijaService notifikacijaService, KorisnikService korisnikService) {
        this.notifikacijaService = notifikacijaService;
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<NotifikacijaDto> getAllNotifikacija() {
        return notifikacijaService.getAllNotifikacija();
    }

    @GetMapping("/{clerkId}")
    public ResponseEntity<List<NotifikacijaDto>> getNotifikacijeKorisnika(@PathVariable("clerkId") String clerkId) {
        Optional<Korisnik> optionalKorisnik = korisnikService.findByClerkUserId(clerkId);
        List<NotifikacijaDto> notifikacijaDtos = new ArrayList<>();
        if(optionalKorisnik.isPresent()) {
            List<Notifikacija> lista = notifikacijaService.getNotifikacijeKorisnika(optionalKorisnik.get().getId());
            for (Notifikacija n : lista) {
                notifikacijaDtos.add(new NotifikacijaDto(n));
            }
        }
        return ResponseEntity.ok(notifikacijaDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotifikacija(@PathVariable Long id) {
        notifikacijaService.deleteNotifikacija(id);
        return ResponseEntity.ok("OBRISAN!");
    }

    @PostMapping("/nova")
    public ResponseEntity<NotifikacijaDto> dodajNovuNotifikaciju(@RequestBody NotifikacijaDto notifikacijaDto) {
        Notifikacija notifikacija = new Notifikacija();
        notifikacija.setStatus("N");
        notifikacija.setSadrzaj(notifikacijaDto.getSadrzaj());
        notifikacija.setVrijemeKreiranja(Instant.now());
        Optional<Korisnik> korisnikOptional = korisnikService.getKorisnikById(notifikacijaDto.getKorisnikId());
        if(korisnikOptional.isPresent()) {
            notifikacija.setKorisnik(korisnikOptional.get());
            Notifikacija sacuvana = notifikacijaService.saveNotifikacija(notifikacija);
            return ResponseEntity.ok(new NotifikacijaDto(sacuvana));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotifikacijaDto> azurirajStatusNotifikacije(@PathVariable Long id, @RequestParam String status) {
        Optional<Notifikacija> notifikacijaOptional = notifikacijaService.getNotifikacijaById(id);
        if(notifikacijaOptional.isPresent()) {
            Notifikacija notifikacija = notifikacijaOptional.get();
            notifikacija.setStatus(status);
            Notifikacija sacuvana = notifikacijaService.saveNotifikacija(notifikacija);
            return ResponseEntity.ok(new NotifikacijaDto(sacuvana));
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
