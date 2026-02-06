package com.jpstechno.edumate_backend.controlleurs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.services.AnneeScolaireService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anneeScolaires")
@CrossOrigin
public class AnneeScolaireControlleur {

    private final AnneeScolaireService anneeScolaireService;

    @PostMapping("/creer")
    public ResponseEntity<AnneeScolaires> creerAnneeScolaire(@RequestBody AnneeScolaires anneeScolaire) {
        AnneeScolaires nouvelleAnnee = anneeScolaireService.creerAnneeScolaire(anneeScolaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleAnnee);
    }

    @GetMapping("/lister")
    public ResponseEntity<List<AnneeScolaires>> listeAnneeScolaires() {
        List<AnneeScolaires> annees = anneeScolaireService.listerAnneesScolaires();
        return ResponseEntity.ok(annees);
    }

}
