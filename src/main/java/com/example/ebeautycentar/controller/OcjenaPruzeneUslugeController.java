package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.OcjenaPruzeneUslugeDto;
import com.example.ebeautycentar.dto.OcjenaPruzeneUslugeKlijentDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import com.example.ebeautycentar.entity.Rezervacija;
import com.example.ebeautycentar.service.KorisnikService;
import com.example.ebeautycentar.service.OcjenaPruzeneUslugeService;
import com.example.ebeautycentar.service.RegistrovaniKlijentService;
import com.example.ebeautycentar.service.RezervacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ocjena_usluge")
public class OcjenaPruzeneUslugeController {

    private final OcjenaPruzeneUslugeService ocjenaPruzeneUslugeService;
    private final RezervacijaService rezervacijaService;
    private final RegistrovaniKlijentService registrovaniKlijentService;
    private final KorisnikService korisnikService;

    @Autowired
    public OcjenaPruzeneUslugeController(OcjenaPruzeneUslugeService ocjenaPruzeneUslugeService, RezervacijaService rezervacijaService, RegistrovaniKlijentService registrovaniKlijentService, KorisnikService korisnikService) {
        this.ocjenaPruzeneUslugeService = ocjenaPruzeneUslugeService;
        this.rezervacijaService = rezervacijaService;
        this.registrovaniKlijentService = registrovaniKlijentService;
        this.korisnikService = korisnikService;
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

    @PostMapping("/nova")
    public ResponseEntity<OcjenaPruzeneUslugeDto> setOcjenaPruzeneUslugeBySalonId(@RequestBody OcjenaPruzeneUslugeKlijentDto dto) {
        OcjenaPruženeUsluge novaOcjena = new OcjenaPruženeUsluge();
        novaOcjena.setOcjena(dto.getOcjena());
        novaOcjena.setKomentar(dto.getKomentar());
        Optional<Rezervacija> rezervacija = rezervacijaService.getRezervacijaById(dto.getRezervacijaId());
        System.out.println(rezervacija);
        Optional<Korisnik> korisnik = korisnikService.findByClerkUserId(dto.getClerkUserId());

        if(rezervacija.isPresent()) {
            Optional<RegistrovaniKlijent> registrovaniKlijent = registrovaniKlijentService.getRegistrovaniKlijentById(korisnik.get().getId());
            if(rezervacija.isPresent() && registrovaniKlijent.isPresent()) {
                novaOcjena.setRegistrovaniKlijent(registrovaniKlijent.get());
                novaOcjena.setRezervacija(rezervacija.get());
                OcjenaPruženeUsluge sacuvana = ocjenaPruzeneUslugeService.sacuvaj(novaOcjena);
                return ResponseEntity.ok(new OcjenaPruzeneUslugeDto(sacuvana));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/zaposleni/{id}")
    public ResponseEntity<List<OcjenaPruzeneUslugeDto>> dohvatiOcjeneZaposlenog(@PathVariable Long id) {
        List<OcjenaPruženeUsluge> ocjene = ocjenaPruzeneUslugeService.ocjeneZaposlenog(id);
        List<OcjenaPruzeneUslugeDto> ocjeneDtos = new ArrayList<>();
        for(OcjenaPruženeUsluge o : ocjene) {
            ocjeneDtos.add(new OcjenaPruzeneUslugeDto(o));
        }
        return ResponseEntity.ok(ocjeneDtos);
    }


}
