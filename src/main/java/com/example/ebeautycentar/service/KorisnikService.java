package com.example.ebeautycentar.service;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService implements UserDetailsService {

    @Autowired
    private final KorisnikRepository korisnikRepository;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    //razmotriti
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik user = korisnikRepository.findByKorisnickoIme(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(user.getKorisnickoIme())
                .password(user.getLozinka())
                .roles("ADMIN")
                .build();
    }

    public List<KorisnikDto> getAllKorisnik() {
        List<Korisnik> korisnikList = korisnikRepository.findAll();
        List<KorisnikDto> korisnikDtoList = new ArrayList<>();
        for(Korisnik korisnik : korisnikList) {
            korisnikDtoList.add(new KorisnikDto(korisnik));
        }
        return korisnikDtoList;
    }

    public Optional<Korisnik> getKorisnikById(Long id) {
        return korisnikRepository.findById(id);
    }

    public Korisnik saveKorisnik(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public void deleteKorisnik(Long id) {
        korisnikRepository.deleteById(id);
    }

    public List<Korisnik> findByStatusAktivan(String status) {
        return korisnikRepository.findByStatus("A");
    }

    public Optional<Korisnik> findByEmail(String email) {
        return korisnikRepository.findByEmail(email);
    }

    public Optional<Korisnik> findByClerkUserId(String clerkUserId) {
        return korisnikRepository.findByClerkUserId(clerkUserId);
    }

    public Optional<Korisnik> findByKorisnickoImeAndLozinka(String korisnickoIme, String lozinka) {
        return korisnikRepository.findByKorisnickoImeAndLozinka(korisnickoIme, lozinka);
    }

    public Optional<Korisnik> findByKorisnickoIme(String korisnickoIme) {
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }


}


