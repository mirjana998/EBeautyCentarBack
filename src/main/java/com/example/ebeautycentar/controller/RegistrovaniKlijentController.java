package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.ClerkRegistrovaniKlijentDto;
import com.example.ebeautycentar.dto.RegistrovaniKlijentDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
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
            // prvo provjera da li postoji korisnik po email-u
            Optional<Korisnik> postojeciKorisnik = korisnikService.findByEmail(noviKlijent.getEmail());

            Korisnik korisnik;

            if (postojeciKorisnik.isPresent()) {
                // Ako već postoji update podatke
                korisnik = postojeciKorisnik.get();
                korisnik.setIme(noviKlijent.getIme());
                korisnik.setPrezime(noviKlijent.getPrezime());
                korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
                korisnik.setBrojTelefona(noviKlijent.getBrojTelefona());
                korisnik.setStatus("A");
                korisnik = korisnikService.saveKorisnik(korisnik);
            } else {
                // Ako ne postoji - kreiranje novog
                korisnik = new Korisnik();
                korisnik.setIme(noviKlijent.getIme());
                korisnik.setPrezime(noviKlijent.getPrezime());
                korisnik.setEmail(noviKlijent.getEmail());
                korisnik.setBrojTelefona(noviKlijent.getBrojTelefona());
                korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
                korisnik.setStatus("A");
                korisnik.setLozinka("korisnik");
                korisnik = korisnikService.saveKorisnik(korisnik);

                // kreiranje registrovanog klijenta samo ako je novi korisnik
                RegistrovaniKlijent registrovaniKlijent = new RegistrovaniKlijent();
                registrovaniKlijent.setKorisnik(korisnik);
                registrovaniKlijent.setBrojTermina(0);
                registrovaniKlijentService.saveRegistrovaniKlijent(registrovaniKlijent);
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




}
