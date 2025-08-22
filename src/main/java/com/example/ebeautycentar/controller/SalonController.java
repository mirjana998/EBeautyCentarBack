package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.RezervacijaSalonDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.Slika;
import com.example.ebeautycentar.service.RezervacijaService;
import com.example.ebeautycentar.service.SalonService;
import com.example.ebeautycentar.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/salon")
public class SalonController {

    private final SalonService salonService;
    private final RezervacijaService rezervacijaService;
    private final SlikaService slikaService;

    @Autowired
    public SalonController(SalonService salonService, RezervacijaService rezervacijaService, SlikaService slikaService) {
        this.salonService = salonService;
        this.rezervacijaService = rezervacijaService;
        this.slikaService = slikaService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long id) {
        Optional<Salon> salon = salonService.getSalonById(id);
        if (salon.isPresent()) {
            // galerija
            List<Slika> galerijaSlika = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.get().getId(), "G");
            List<String> galerija = galerijaSlika.stream().map(Slika::getNaziv).toList();

            // naslovna
            List<Slika> naslovne = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.get().getId(), "N");
            String naslovnaSlikaNaziv = null;
            if (naslovne != null && !naslovne.isEmpty()) {
                naslovnaSlikaNaziv = naslovne.get(0).getNaziv();
            }

            SalonDto salonDto = new SalonDto(salon.get(), naslovnaSlikaNaziv, galerija);
            return ResponseEntity.ok(salonDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
        return ResponseEntity.ok("OBRISAN!");
    }

    @GetMapping
    public ResponseEntity<List<SalonDto>> pretragaSalona(@RequestParam(required = false) String grad, @RequestParam(required = false) Integer usluga, @RequestParam(required = false) String naziv) {
        List<SalonDto> lista = salonService.getSaloniByGradAndUslugaAndNaziv(grad, usluga, naziv);
        for (SalonDto salon : lista) {
            List<Slika> galerijaSlika = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.getId(), "G");
            List<String> galerija = galerijaSlika.stream().map(Slika::getNaziv).toList();
            List<Slika> naslovne = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.getId(), "N");
            salon.setGalerijaSlika(galerija);
            if(!naslovne.isEmpty()) {
                salon.setNaslovnaSlika(naslovne.get(0).getNaziv());
            }
        }
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/popularni")
    public ResponseEntity<List<SalonDto>> getPopularniSaloni() {
        List<SalonDto>saloni=salonService.getPopularniSaloni();

        for (SalonDto salon : saloni) {
            List<Slika> galerijaSlika = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.getId(), "G");
            List<String> galerija = galerijaSlika.stream().map(Slika::getNaziv).toList();
            List<Slika> naslovne = this.slikaService.getBySalonIdAndStatusAndVrsta(salon.getId(), "N");
            salon.setGalerijaSlika(galerija);
            if(!naslovne.isEmpty()) {
                salon.setNaslovnaSlika(naslovne.get(0).getNaziv());
            }
        }
        if (saloni.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else if(saloni.size()<4) {
            return ResponseEntity.ok(saloni);
        }else {
            return ResponseEntity.ok(saloni.subList(0,4));
        }
    }

    @GetMapping("/{id}/rezervacije")
   public ResponseEntity<List<RezervacijaSalonDto>>getRezervacijeZaSalon(@PathVariable Long id)
    {
        List<RezervacijaSalonDto>rezervacije=rezervacijaService.getRezervacijaBySalonId(id);
        if (rezervacije.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rezervacije);
    }
}
