package com.jpstechno.edumate_backend.serviceimplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.KeyGenerator.CustomIdGeneratorService;
import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.Parents;
import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;
import com.jpstechno.edumate_backend.repositories.CandidatAdmissionRepo;
import com.jpstechno.edumate_backend.repositories.DemandeAdmissionRepo;
import com.jpstechno.edumate_backend.services.DemandeAdmissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandeAdmissionImplementation implements DemandeAdmissionService {

    private final DemandeAdmissionRepo demandeAdmissionRepo;
    private final CandidatAdmissionRepo candidatAdmissionRepo;
    private final CustomIdGeneratorService idGenerator;
    private final ApplicationEventPublisher admissionPublisher;

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParAnnee() {
        return null;
    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId) {
        return null;
    }

    @Override
    public void supprimerDemandeAdmission(Long eleveId, String anneeScolaire) {

    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParClasseEtAnnee(Long classeId, String anneeScolaire) {
        return null;
    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat) {
        return demandeAdmissionRepo.findAll().stream()
                .filter(demande -> demande.getStatutDemande().toString().equalsIgnoreCase(etat))
                .toList();
    }

    @Override
    @Transactional
    public DemandeAdmissions creerDemandeAdmission(DemandeAdmissions admissionData) {

        // verifier si candidat existait, si non, creer un nouveau candidat
        String nomCandidat = admissionData.getCandidatAdmission().getNom();
        String prenomCandidat = admissionData.getCandidatAdmission().getPrenom();
        LocalDate dateNaissance = admissionData.getCandidatAdmission().getDateNaissance();

        CandidatAdmission candidat = candidatAdmissionRepo
                .getCandidatByNomCompletDateNaissance(nomCandidat, prenomCandidat, dateNaissance)
                .orElseGet(() -> {
                    return candidatAdmissionRepo.save(admissionData.getCandidatAdmission());
                });
        admissionData.setCandidatAdmission(candidat);

        // verifier si une demande d'admission n'existait pas pour ce candidat
        // pour la meme annee et la meme classe
        if (demandeAdmissionRepo.findByCandidatAnneeClasse(candidat, admissionData.getAnneeScolaire(),
                admissionData.getClasseSouhaitee()).isPresent()) {
            throw new RuntimeException("Une demande d'admission existe deja pour ce candidat");
        } else {
            String numeroDemande = idGenerator.nextId("DA");
            admissionData.setNumeroDemande(numeroDemande);
            admissionData.setDateDemandeAdmission(LocalDate.now());
            admissionData.setStatutDemande(StatutDemandeAdmission.EN_ATTENTE);

            if (admissionData.getCandidatAdmission().getParents() != null) { // lier candidat au parent
                admissionData.getCandidatAdmission().getParents().forEach((paren -> paren.setCandidat(candidat)));
                candidat.setParents(admissionData.getCandidatAdmission().getParents());
                candidatAdmissionRepo.save(candidat);
            }
            ;

            if (admissionData.getDocumentsJoint() != null) { // lier document a admission
                admissionData.getDocumentsJoint().forEach((doc) -> doc.setDemandeAdmission(admissionData));
            }

            DemandeAdmissions nouvelleDemande = demandeAdmissionRepo.save(admissionData);
            // publier l'evenement nouvelleDemande en vue notification mail candidat et
            // facturation frais d'etudes
            admissionPublisher.publishEvent(nouvelleDemande);
            return nouvelleDemande;
        }

    }

    @Override
    public List<DemandeAdmissions> listerDemandeAdmission() {
        Sort trieAnneeStatut = Sort.by(Sort.Direction.ASC, "anneeScolaire");
        List<DemandeAdmissions> reponseData = demandeAdmissionRepo.findAll(trieAnneeStatut);
        return reponseData;
    }

}
