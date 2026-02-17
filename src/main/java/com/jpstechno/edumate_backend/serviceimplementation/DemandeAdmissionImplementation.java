package com.jpstechno.edumate_backend.serviceimplementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.KeyGenerator.CustomIdGeneratorService;
import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.ClesComposes.DemandeAdmissionKey;
import com.jpstechno.edumate_backend.modeles.dto.DemandeAdmissionForm;
import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;
import com.jpstechno.edumate_backend.repositories.AnneeScolaireRepo;
import com.jpstechno.edumate_backend.repositories.CandidatAdmissionRepo;
import com.jpstechno.edumate_backend.repositories.ClassesRepo;
import com.jpstechno.edumate_backend.repositories.DemandeAdmissionRepo;
import com.jpstechno.edumate_backend.services.DemandeAdmissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemandeAdmissionImplementation implements DemandeAdmissionService {

    private final DemandeAdmissionRepo demandeAdmissionRepo;
    private final AnneeScolaireRepo anneeScolaireRepo;
    private final ClassesRepo classesRepo;
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
        return demandeAdmissionRepo.findAll().stream()
                .filter(demande -> demande.getAdmissionId().getClasseId().equals(classeId)
                        && demande.getAnneeScolaire().getAnneeScolaire().equalsIgnoreCase(anneeScolaire))
                .toList();
    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat) {
        return demandeAdmissionRepo.findAll().stream()
                .filter(demande -> demande.getStatutDemande().toString().equalsIgnoreCase(etat))
                .toList();
    }

    @Override
    @Transactional
    public DemandeAdmissions creerDemandeAdmission(DemandeAdmissionForm admissionData) {

        // verifier si le candidat existe deja, sinon creer un nouveau candidat
        CandidatAdmission candidat = candidatAdmissionRepo.findByEmailIgnoreCase(admissionData.getEmail())
                .orElseGet(() -> {

                    CandidatAdmission nouvoCandidat = new CandidatAdmission();
                    nouvoCandidat.setAdresse(admissionData.getAdresse());

                    // d'abord convertir la date de naissance recu au format string en format
                    // LocalDate
                    LocalDate convertedDateOfBirth = LocalDate.parse(admissionData.getDateNaissance());
                    nouvoCandidat.setDateNaissance(convertedDateOfBirth);

                    nouvoCandidat.setEmail(admissionData.getEmail());
                    nouvoCandidat.setNom(admissionData.getNom());
                    nouvoCandidat.setNumeroTelephone(admissionData.getNumeroTelephone());
                    nouvoCandidat.setPrenom(admissionData.getPrenom());
                    nouvoCandidat.setSexe(admissionData.getSexe());
                    return candidatAdmissionRepo.save(nouvoCandidat);
                });

        // verifier et recuperer la classe souhaiter pour admission
        Classes classeSouhaite = classesRepo.findByNomClasse(admissionData.getClasseSouhaitee()).get();

        // verifier et recuperer l'annee scolaire pour commencer etude dans l'ecole
        AnneeScolaires anneeScolaire = anneeScolaireRepo.findByAnneeScolaire(admissionData.getAnneeScolaire()).get();

        // construire la cle preimaire (cle composee) pour la nouvelle demande
        // d'admission
        DemandeAdmissionKey admKey = new DemandeAdmissionKey();
        admKey.setAnneeScolaireId(classeSouhaite.getId());
        admKey.setCandidatId(candidat.getId());
        admKey.setClasseId(classeSouhaite.getId());

        // verifier si une demande n'existait pas pour meme annee, meme classe et meme
        // personne
        if (demandeAdmissionRepo.findById(admKey).isPresent()) {
            throw new RuntimeException("Une demande d'admission existe deja pour ce candidat");
        } else {
            DemandeAdmissions nouvelleDemande = new DemandeAdmissions();
            // generer le numero de la demande
            String numeroDemande = idGenerator.nextId("DA");

            nouvelleDemande.setNumeroDemande(numeroDemande);
            nouvelleDemande.setAdmissionId(admKey);
            nouvelleDemande.setAnneeScolaire(anneeScolaire);
            nouvelleDemande.setCandidatAdmission(candidat);
            nouvelleDemande.setDateDemandeAdmission(LocalDate.now());
            nouvelleDemande.setClasseSouhaitee(classeSouhaite);
            nouvelleDemande.setStatutDemande(StatutDemandeAdmission.EN_ATTENTE);
            nouvelleDemande.setMotivation(admissionData.getMotivation());
            nouvelleDemande.setDocumentsJoint(admissionData.getListeDocs());

            // publier l'evenement nouvelleDemande pour email candidat et facturation
            admissionPublisher.publishEvent(nouvelleDemande);

            return demandeAdmissionRepo.save(nouvelleDemande);
        }

    }

    @Override
    public List<DemandeAdmissions> listerDemandeAdmission() {
        Sort trieAnneeStatut = Sort.by(Sort.Direction.ASC, "anneeScolaire");
        List<DemandeAdmissions> reponseData = demandeAdmissionRepo.findAll(trieAnneeStatut);
        return reponseData;
    }

}
