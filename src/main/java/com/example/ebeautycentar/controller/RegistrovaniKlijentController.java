package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.ClerkRegistrovaniKlijentDto;
import com.example.ebeautycentar.dto.RegistrovaniKlijentDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import com.example.ebeautycentar.entity.Uloga;
import com.example.ebeautycentar.service.ClerkService;
import com.example.ebeautycentar.service.KorisnikService;
import com.example.ebeautycentar.service.RegistrovaniKlijentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/registrovani_klijent")
public class RegistrovaniKlijentController {

    private final RegistrovaniKlijentService registrovaniKlijentService;
    private final KorisnikService korisnikService;
    private final ClerkService clerkService;

    @Autowired
    public RegistrovaniKlijentController(
            RegistrovaniKlijentService registrovaniKlijentService,
            KorisnikService korisnikService,
            ClerkService clerkService
    ) {
        this.registrovaniKlijentService = registrovaniKlijentService;
        this.korisnikService = korisnikService;
        this.clerkService = clerkService;
    }

    @GetMapping
    public List<RegistrovaniKlijentDto> getAllRegistrovaniKlijent() {
        return registrovaniKlijentService.getAllRegistrovaniKlijent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrovaniKlijentDto> getRegistrovaniKlijentById(@PathVariable Long id) {
        Optional<RegistrovaniKlijent> registrovaniKlijent = registrovaniKlijentService.getRegistrovaniKlijentById(id);
        if (registrovaniKlijent.isPresent()) {
            RegistrovaniKlijentDto registrovaniKlijentDto = new RegistrovaniKlijentDto(registrovaniKlijent.get());
            return ResponseEntity.ok(registrovaniKlijentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registracija")
    public ResponseEntity<RegistrovaniKlijentDto> registracija(@RequestBody ClerkRegistrovaniKlijentDto noviKlijent) {
        try {
            // 1. prvo provjera po clerkUserId
            Optional<Korisnik> postojeciKorisnik = korisnikService.findByClerkUserId(noviKlijent.getClerkUserId());

            Korisnik korisnik;

            if (postojeciKorisnik.isPresent()) {
                // update podataka ako vec postoji po clerkUserId
                korisnik = postojeciKorisnik.get();
                korisnik.setIme(noviKlijent.getIme());
                korisnik.setPrezime(noviKlijent.getPrezime());
                korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
                korisnik.setBrojTelefona(noviKlijent.getBrojTelefona());
                korisnik.setEmail(noviKlijent.getEmail());
                korisnik.setStatus("A");
                korisnik = korisnikService.saveKorisnik(korisnik);

            } else {
                // 2. provjera po emailu (ako postoji sa statusom D reaktiviraj)
                Optional<Korisnik> korisnikPoEmailu = korisnikService.findByEmail(noviKlijent.getEmail());
                if (korisnikPoEmailu.isPresent()) {
                    korisnik = korisnikPoEmailu.get();
                    korisnik.setIme(noviKlijent.getIme());
                    korisnik.setPrezime(noviKlijent.getPrezime());
                    korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
                    korisnik.setBrojTelefona(noviKlijent.getBrojTelefona());
                    korisnik.setClerkUserId(noviKlijent.getClerkUserId());
                    korisnik.setStatus("A");
                    korisnik = korisnikService.saveKorisnik(korisnik);

                } else {
                    // 3. kreiranje potpuno novog korisnika
                    korisnik = new Korisnik();
                    korisnik.setIme(noviKlijent.getIme());
                    korisnik.setPrezime(noviKlijent.getPrezime());
                    korisnik.setEmail(noviKlijent.getEmail());
                    korisnik.setBrojTelefona(noviKlijent.getBrojTelefona());
                    korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
                    korisnik.setStatus("A");
                    korisnik.setLozinka("korisnik");
                    korisnik.setClerkUserId(noviKlijent.getClerkUserId());


                    Uloga defaultUloga = new Uloga();
                    defaultUloga.setId(1);
                    korisnik.setUloga(defaultUloga);

                    korisnik = korisnikService.saveKorisnik(korisnik);

                    RegistrovaniKlijent registrovaniKlijent = new RegistrovaniKlijent();
                    registrovaniKlijent.setKorisnik(korisnik);
                    registrovaniKlijent.setBrojTermina(0);
                    registrovaniKlijentService.saveRegistrovaniKlijent(registrovaniKlijent);
                }
            }

            Optional<RegistrovaniKlijent> registrovani = registrovaniKlijentService.getByKorisnikId(korisnik.getId());
            return registrovani
                    .map(rk -> ResponseEntity.ok(new RegistrovaniKlijentDto(rk)))
                    .orElse(ResponseEntity.ok().build());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }



    @PostMapping("/delete")
    public ResponseEntity<Void> deactivateAndDeleteByEmail(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");

            Optional<Korisnik> korisnikOpt = korisnikService.findByEmail(email);
            if (korisnikOpt.isPresent()) {
                Korisnik korisnik = korisnikOpt.get();
                korisnik.setStatus("D");
                korisnikService.saveKorisnik(korisnik);
            }

            boolean deleted = clerkService.deleteUserByEmail(email);

            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.status(500).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/by-clerk/{clerkUserId}")
    public ResponseEntity<RegistrovaniKlijentDto> getRegistrovaniKlijentByClerk(@PathVariable String clerkUserId) {
        Optional<Korisnik> korisnikOpt = korisnikService.findByClerkUserId(clerkUserId);
        if (korisnikOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Korisnik korisnik = korisnikOpt.get();

        Optional<RegistrovaniKlijent> registrovaniOpt =
                registrovaniKlijentService.getByKorisnikId(korisnik.getId());

        if (registrovaniOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        RegistrovaniKlijentDto dto = new RegistrovaniKlijentDto(registrovaniOpt.get());
        return ResponseEntity.ok(dto);
    }




}






