package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.ClerkRegistrovaniKlijentDto;
import com.example.ebeautycentar.dto.RegistrovaniKlijentDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import com.example.ebeautycentar.service.RegistrovaniKlijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/registrovani_klijent")
public class RegistrovaniKlijentController {

    private final RegistrovaniKlijentService registrovaniKlijentService;

    @Autowired
    public RegistrovaniKlijentController(RegistrovaniKlijentService registrovaniKlijentService) {
        this.registrovaniKlijentService = registrovaniKlijentService;
    }

    @GetMapping
    public List<RegistrovaniKlijentDto> getAllRegistrovaniKlijent() {
        return registrovaniKlijentService.getAllRegistrovaniKlijent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrovaniKlijentDto> getRegistrovaniKlijentById(@PathVariable Long id) {
        Optional<RegistrovaniKlijent> registrovaniKlijent = registrovaniKlijentService.getRegistrovaniKlijentById(id);
        if(registrovaniKlijent.isPresent()) {
            RegistrovaniKlijentDto registrovaniKlijentDto = new RegistrovaniKlijentDto(registrovaniKlijent.get());
            return ResponseEntity.ok(registrovaniKlijentDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registracija")
    public ResponseEntity<RegistrovaniKlijentDto> registracija(@RequestBody ClerkRegistrovaniKlijentDto noviKlijent) {
        RegistrovaniKlijent registrovaniKlijent = new RegistrovaniKlijent();
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(noviKlijent.getIme());
        korisnik.setPrezime(noviKlijent.getPrezime());
        korisnik.setEmail(noviKlijent.getEmail());
        korisnik.setBrojTelefona(noviKlijent.getTelefon());
        korisnik.setKorisnickoIme(noviKlijent.getKorisnickoIme());
        String temp = korisnik.getEmail() + "-" + korisnik.getId();
        korisnik.setStatus("A");
        korisnik.setLozinka("korisnik");
        /*
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(temp.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            System.out.println("SHA-256 Hash: " + hexString.toString());
            korisnik.setLozinka(hexString.toString());
        } catch ( NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        registrovaniKlijent.setKorisnik(korisnik);
        registrovaniKlijent.setBrojTermina(0);

        RegistrovaniKlijent rk = registrovaniKlijentService.saveRegistrovaniKlijent(registrovaniKlijent);
        return ResponseEntity.ok(new RegistrovaniKlijentDto(rk));
    }
}
