package com.jpstechno.edumate_backend.controlleurs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;
import com.jpstechno.edumate_backend.modeles.OldStudentRegistrationData;
import com.jpstechno.edumate_backend.services.EtudiantService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/etudiants")
@RequiredArgsConstructor
public class EtudiantControlleur {

    private final EtudiantService etudiantService;

    @PostMapping("/enroler_ancien_etudiant")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> enrolerAncienEtudiant(@RequestBody OldStudentRegistrationData oldStudentData) {
        try {
            etudiantService.enrolerAncienEtudiant(oldStudentData);
            return new ResponseEntity<>("Enrollement de l'ancien etudiant reussi", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Echec de l'enrollement de l'ancien etudiant: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getStudentHistorique")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getConnectedStudentClasseHistorique() {
        try {
            List<HistoriqueClassesEtudiant> histo = etudiantService.getConnectedStudentData();
            return new ResponseEntity<>(histo, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Echec de chargement des donnees " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
