package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.RezervacijaDto;
import com.example.ebeautycentar.dto.RezervacijaKlijentDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.dto.ZaposleniDto;
import com.example.ebeautycentar.entity.*;
import com.example.ebeautycentar.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

    public final RezervacijaService rezervacijaService;
    public final SalonUslugaService salonUslugaService;
    public final RadnoVrijemeService radnoVrijemeService;
    public final ZaposleniService zaposleniService;
    private final KorisnikService korisnikService;

    public RezervacijaController(RezervacijaService rezervacijaService, SalonUslugaService salonUslugaService, RadnoVrijemeService radnoVrijemeService, ZaposleniService zaposleniService, KorisnikService korisnikService) {
        this.rezervacijaService = rezervacijaService;
        this.salonUslugaService = salonUslugaService;
        this.radnoVrijemeService = radnoVrijemeService;
        this.zaposleniService = zaposleniService;
        this.korisnikService = korisnikService;
    }

    public static boolean istiDan(Instant instant1, Instant instant2) {
        ZoneId zoneId = ZoneId.of("Europe/Belgrade");
        LocalDate date1 = instant1.atZone(zoneId).toLocalDate();
        LocalDate date2 = instant2.atZone(zoneId).toLocalDate();
        System.out.println("Date1: " + date1 + ", Date2: " + date2 + " E: " + date1.isEqual(date2) );
        return date1.isEqual(date2);
    }

    public static boolean uporediVremenaRezervacije(Rezervacija zakazanaRezervacija, Instant pocetakUpitneRezervacije, Instant zavrsetakUpitneRezervacije) {
        SalonUsluga uslugaZakazaneRezervacije = zakazanaRezervacija.getZaposleniSalonUsluga().getSalonUsluga();
        Instant pocetakZakazaneRezervacije = zakazanaRezervacija.getTerminPocetkaUsluge();
        Instant zavrsetakZakazaneRezervacije = pocetakZakazaneRezervacije.plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getHour(),ChronoUnit.HOURS).plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getMinute(),ChronoUnit.MINUTES).plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getSecond(),ChronoUnit.SECONDS);
        if(zavrsetakUpitneRezervacije.isBefore(pocetakZakazaneRezervacije) || pocetakUpitneRezervacije.isAfter(zavrsetakZakazaneRezervacije) || pocetakUpitneRezervacije.equals(zavrsetakZakazaneRezervacije) || zavrsetakUpitneRezervacije.equals(pocetakZakazaneRezervacije)) {
           // System.out.println("Nema preklapanja: " + zakazanaRezervacija.getTerminPocetkaUsluge() + " " + pocetakUpitneRezervacije);
            return false;
        }
        // System.out.println("PREKLAPANJE: " + zakazanaRezervacija.getTerminPocetkaUsluge() + " " + pocetakUpitneRezervacije);
        return true;

    }


    @GetMapping("/provjera")
    public ResponseEntity<String> provjeriSlobodnuRezervaciju(@RequestParam Long usluga, @RequestParam Long zaposleni, @RequestParam("datum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant datum) {

        Optional<SalonUsluga> salonUslugaOptional = salonUslugaService.getSalonUslugaById(usluga);
        Optional<Zaposleni> zaposleniOptional = zaposleniService.getZaposleniById(zaposleni);
        if (salonUslugaOptional.isPresent() && zaposleniOptional.isPresent()) {
            SalonUsluga salonUsluga = salonUslugaOptional.get();

            // prvo odredi vrijeme zavrsetka rezervacije za koju se salje upit
            Instant datumZavrsetkaRezervacije = datum.plus(salonUsluga.getTrajanjeUsluge().getHour(), ChronoUnit.HOURS).plus(salonUsluga.getTrajanjeUsluge().getMinute(), ChronoUnit.MINUTES).plus(salonUsluga.getTrajanjeUsluge().getSecond(), ChronoUnit.SECONDS);
            System.out.println("planirani datum zavrsetka: " + datumZavrsetkaRezervacije);

            List<Rezervacija> rezervacijeZaposlenog = rezervacijaService.getRezervacijaByZaposleniId(zaposleni);

            List<Rezervacija> rezervacijeIstiDan = rezervacijeZaposlenog.stream().filter(rezervacija ->
                    istiDan(rezervacija.getTerminPocetkaUsluge(), datum)).toList();

            List<Rezervacija> preklapajuceRezervacije = rezervacijeIstiDan.stream().filter(rezervacija ->
                    uporediVremenaRezervacije(rezervacija, datum, datumZavrsetkaRezervacije)).toList();
            preklapajuceRezervacije.stream().forEach(rezervacija -> {
                System.out.println("Preklapanje: " + rezervacija.getId() + " " + rezervacija.getZaposleniSalonUsluga().getZaposleni().getId() + " " + rezervacija.getTerminPocetkaUsluge());
            });
            if (preklapajuceRezervacije.isEmpty()) {
                return ResponseEntity.ok("SLOBODAN TERMIN!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NIJE SLOBODAN TERMIN!");
            }
        } else if (zaposleniOptional.isPresent() && salonUslugaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USLUGA NIJE PRONADJENA!");
        } else if (zaposleniOptional.isEmpty() && salonUslugaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ZAPOSLENI NIJE PRONADJEN!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USLUGA I ZAPOSLENI NISU PRONADJENI!");
        }

    }

    @GetMapping("/vrijeme")
    public ResponseEntity<List<Instant>> dohvatiVremena(@RequestParam Long usluga, @RequestParam Long zaposleni, @RequestParam("datum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant datum) {
        List<LocalTime> termini = new ArrayList<>();
        List<Instant> terminiInstant = new ArrayList<>();

        Optional<SalonUsluga> salonUsluga = salonUslugaService.getSalonUslugaById(usluga);
        Optional<Zaposleni> zaposleniOptional = zaposleniService.getZaposleniById(zaposleni);

        if(salonUsluga.isPresent() && zaposleniOptional.isPresent()) {
            RadnoVrijeme radnoVrijeme = radnoVrijemeService.findRadnoVrijemeByDanUSedmiciIdAndSalonId(datum.atZone(ZoneId.systemDefault()).getDayOfWeek().getValue(),salonUsluga.get().getSalon().getId());
            System.out.println(radnoVrijeme.getPocetakRadnoVrijeme());
            System.out.println(radnoVrijeme.getKrajRadnoVrijeme());
            System.out.println(salonUsluga.get().getTrajanjeUsluge());
            long pocetak = radnoVrijeme.getPocetakRadnoVrijeme().getLong(ChronoField.NANO_OF_DAY);
            long kraj = radnoVrijeme.getKrajRadnoVrijeme().getLong(ChronoField.NANO_OF_DAY);
            long trajanje = salonUsluga.get().getTrajanjeUsluge().getLong(ChronoField.NANO_OF_DAY);
            int j = 0;
            for(long i = pocetak ; i < kraj; i+=trajanje) {
                LocalTime temp;
                if (termini.isEmpty()) {
                    temp = radnoVrijeme.getPocetakRadnoVrijeme();
                } else {
                    temp = termini.get(termini.size() - 1);
                    temp = temp.plusHours(salonUsluga.get().getTrajanjeUsluge().getHour()).plusMinutes(salonUsluga.get().getTrajanjeUsluge().getMinute()).plusSeconds(salonUsluga.get().getTrajanjeUsluge().getSecond());
                }
                if(temp.plusNanos(trajanje).getLong(ChronoField.NANO_OF_DAY) < kraj) {
                    termini.add(j, temp);
                    ZonedDateTime zdt = datum.atZone(ZoneId.systemDefault());
                    Instant tempInstant = zdt.withHour(temp.getHour()+2).withMinute(temp.getMinute()).withSecond(temp.getSecond()).toInstant();
                    terminiInstant.add(tempInstant);
                    j++;
                }
            }

            //dohvata sve vec unesene rezervacije tog zaposlenog
            List<Rezervacija> rezervacijeZaposlenog = rezervacijaService.getRezervacijaByZaposleniId(zaposleni);
            //filtrira iste rezervacije izdvajajuci one koje su zakazane na taj datum
            List<Rezervacija> rezervacijeIstiDan = rezervacijeZaposlenog.stream().filter(rezervacija ->
                    istiDan(rezervacija.getTerminPocetkaUsluge(), datum)).toList();
                       rezervacijeIstiDan.stream().forEach(rezervacija -> {
            System.out.println("Dan: " + rezervacija.getId() + " " + rezervacija.getZaposleniSalonUsluga().getZaposleni().getId() + " " + rezervacija.getTerminPocetkaUsluge());
          });

            terminiInstant = terminiInstant.stream().filter(termin->provjeriPreklapanja(rezervacijeIstiDan,termin,termin.plus(salonUsluga.get().getTrajanjeUsluge().getHour(), ChronoUnit.HOURS).plus(salonUsluga.get().getTrajanjeUsluge().getMinute(), ChronoUnit.MINUTES).plus(salonUsluga.get().getTrajanjeUsluge().getSecond(), ChronoUnit.SECONDS))).toList();
            return ResponseEntity.ok(terminiInstant);

        }else {
            return ResponseEntity.notFound().build();
        }

    }

    public boolean provjeriPreklapanja(List<Rezervacija> lista, Instant datumPocetka, Instant datumKraja) {
        List<Rezervacija> preklapajuceRezervacije = lista.stream().filter(rezervacija ->
                uporediVremenaRezervacije(rezervacija, datumPocetka, datumKraja)).toList();
        if(preklapajuceRezervacije.isEmpty()) {
            return true;
        }
        return false;
    }

    @PatchMapping("/otkazivanje/{id}")
    public ResponseEntity<RezervacijaDto> otkaziRezervaciju(@PathVariable Long id, @RequestParam String fleg) {
        Optional<Rezervacija> rezervacijaOptional = rezervacijaService.getRezervacijaById(id);
        if(rezervacijaOptional.isPresent()) {
            if("S".equals(rezervacijaOptional.get().getStatus())) {
                return ResponseEntity.ok(new RezervacijaDto(rezervacijaOptional.get()));
            }
            Rezervacija rezervacija = rezervacijaOptional.get();
            rezervacija.setStatus("S");
            if("K".equals(fleg)) {
                rezervacija.setVrijemeOtkazivanjaKlijent(Instant.now());
            }else if("V".equals(fleg)) {
                rezervacija.setVrijemeOtkazivanjaVlasnik(Instant.now());
            }
            Rezervacija sacuvana = rezervacijaService.saveRezervacija(rezervacija);
            return ResponseEntity.ok(new RezervacijaDto(sacuvana));

        }else {
            return ResponseEntity.notFound().build();
        }
    }

        @GetMapping("/klijent")
    public ResponseEntity<List<RezervacijaKlijentDto>> getRezervacijeKlijenta(@RequestParam String clerkUserId) {
        Optional<Korisnik> korisnikOptional = korisnikService.findByClerkUserId(clerkUserId);
        int i = 1;
        if(korisnikOptional.isPresent()) {
            List<Rezervacija> rezervacijeKlijenta = rezervacijaService.getRezervacijeKlijenta(korisnikOptional.get().getId());
            System.out.println(rezervacijeKlijenta.size());
            List<RezervacijaKlijentDto> rezervacijeKlijentDtos = new ArrayList<>();
            for(Rezervacija r : rezervacijeKlijenta) {
                System.out.println(i++);
                RezervacijaKlijentDto rezervacijaDto = new RezervacijaKlijentDto();
                rezervacijaDto.setId(r.getId());
                rezervacijaDto.setSalon(new SalonDto(r.getZaposleniSalonUsluga().getSalonUsluga().getSalon()));
                rezervacijaDto.setNazivUsluge(r.getZaposleniSalonUsluga().getSalonUsluga().getUsluga().getNaziv());
                rezervacijaDto.setZaposleni(new ZaposleniDto(r.getZaposleniSalonUsluga().getZaposleni()));
                rezervacijaDto.setStatusRezervacije(r.getStatus());
                rezervacijaDto.setVrijemePocetka(r.getTerminPocetkaUsluge());
                rezervacijaDto.setClerkUserId(korisnikOptional.get().getClerkUserId());
                rezervacijeKlijentDtos.add(rezervacijaDto);
            }
            return ResponseEntity.ok(rezervacijeKlijentDtos);
        }
        return ResponseEntity.noContent().build();

    }

}
