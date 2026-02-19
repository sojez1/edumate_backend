package com.jpstechno.edumate_backend.controlleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.AdmissionDecisions;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.DocumentsJoints;
import com.jpstechno.edumate_backend.modeles.dto.DemandeAdmissionForm;
import com.jpstechno.edumate_backend.modeles.dto.GetAdmissionDetailsForm;

import com.jpstechno.edumate_backend.services.DecisionAdmissionService;
import com.jpstechno.edumate_backend.services.DemandeAdmissionService;
import com.jpstechno.edumate_backend.services.DocumentsJointService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("demande-admission")
@CrossOrigin
public class DemandeAdmissionControlleur {

    private final DemandeAdmissionService demandeAdmissionService;
    private final DocumentsJointService docsJointService;
    private final DecisionAdmissionService decisionAdmissionService;

    @PostMapping("/soumettre")
    public ResponseEntity<String> soumettreDemandeAdmission(@RequestBody DemandeAdmissionForm demandeAdmission) {
        DemandeAdmissions nouvelleDemande = demandeAdmissionService.creerDemandeAdmission(demandeAdmission);
        String message = String.format("Votre demande d'admission est cree sous le numero %s",
                nouvelleDemande.getNumeroDemande());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/liste-demande-admission")
    public ResponseEntity<List<GetAdmissionDetailsForm>> listeDemandeAdmission() {
        List<DemandeAdmissions> reponse = demandeAdmissionService.listerDemandeAdmission();
        List<GetAdmissionDetailsForm> result = new ArrayList<>();
        for (DemandeAdmissions eachDemande : reponse) {
            GetAdmissionDetailsForm res = new GetAdmissionDetailsForm(
                    eachDemande.getCandidatAdmission().getDateNaissance().toString(),
                    eachDemande.getAnneeScolaire().getAnneeScolaire(), eachDemande.getNumeroDemande(),
                    eachDemande.getCandidatAdmission().getNom() + " " + eachDemande.getCandidatAdmission().getPrenom(),
                    eachDemande.getClasseSouhaitee().getNomClasse(), eachDemande.getStatutDemande().name(),
                    eachDemande.getDateDemandeAdmission().toString());
            result.add(res);

        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/documets_par_demande_admission")
    public ResponseEntity<List<DocumentsJoints>> getDocumentsJointParDemandeAdmission(String numeroDemandeAdmission) {
        List<DocumentsJoints> lesDocsJoints = docsJointService
                .getAllDocumentsJointPourAdmission(numeroDemandeAdmission);
        return new ResponseEntity<>(lesDocsJoints, HttpStatus.OK);

    }

    @PostMapping("/prendre_decision")
    public ResponseEntity<AdmissionDecisions> prendreUneDecision(AdmissionDecisions decisions) {
        AdmissionDecisions NouvelleDecision = decisionAdmissionService.ajouterUneDecision(decisions);
        return new ResponseEntity<>(NouvelleDecision, HttpStatus.CREATED);

    }

    @GetMapping("/mes_decisions_admission/{numeroDemande}")
    public ResponseEntity<List<AdmissionDecisions>> mesDecisionsAdmission(String numeroAdmission) {
        List<AdmissionDecisions> result = decisionAdmissionService.getAllDecisionParDemande(numeroAdmission);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
