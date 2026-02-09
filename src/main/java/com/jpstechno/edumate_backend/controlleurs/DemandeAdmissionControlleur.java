package com.jpstechno.edumate_backend.controlleurs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.services.DemandeAdmissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("demande-admission")
public class DemandeAdmissionControlleur {

    private final DemandeAdmissionService demandeAdmissionService;

    @PostMapping("/soumettre")
    public ResponseEntity<DemandeAdmissions> soumettreDemandeAdmission(
            @RequestBody DemandeAdmissions demandeAdmission) {
        DemandeAdmissions nouvelleDemande = demandeAdmissionService.creerDemandeAdmission(demandeAdmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleDemande);
    }

}
