package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.AuthRequest;
import com.example.ebeautycentar.dto.KorisnikAuthDto;
import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.service.JwtService;
import com.example.ebeautycentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    @Autowired
    private final KorisnikService korisnikService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public KorisnikController(KorisnikService korisnikService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.korisnikService = korisnikService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> chechLogin(@RequestBody AuthRequest ar) {
        System.out.println("Unutar auth");
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(ar.getUsername(),ar.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
        Optional<Korisnik> korisnik = korisnikService.findByKorisnickoIme(ar.getUsername());
        if (korisnik.isEmpty()) {
            throw  new UsernameNotFoundException("Username does not exist!");
        }
        Korisnik k = korisnik.get();
        String rola = k.getUloga().getNaziv();
        if (k.getKorisnickoIme().equals(ar.getUsername()) && k.getLozinka().equals(ar.getPassword())) {
            //KorisnikAuthDto korisnikAuthDto = new KorisnikAuthDto(k);
           // korisnikAuthDto.setToken();
            return ResponseEntity.ok(jwtService.generateToken(ar.getUsername(), rola, k.getId()));
        }else{
            throw new UsernameNotFoundException("Username or password is incorrect");
        }
    }

    @GetMapping
    public List<KorisnikDto> getAllKorisnik() {
        return korisnikService.getAllKorisnik();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getKorisnikById(@PathVariable Long id) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikById(id);
        if(korisnik.isPresent()) {
            KorisnikDto korisnikDto = new KorisnikDto(korisnik.get());
            return ResponseEntity.ok(korisnikDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}

