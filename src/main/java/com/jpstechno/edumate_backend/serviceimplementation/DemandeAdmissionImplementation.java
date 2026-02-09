package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.ClesComposes.DemandeAdmissionKey;
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

    @Override
    @Transactional
    public DemandeAdmissions creerDemandeAdmission(String anneeScolaire, String classeSouhaitee, String nom,
            String prenom, String email, String telephone, String adresse) {

        AnneeScolaires annee = anneeScolaireRepo.findByAnneeScolaire(anneeScolaire).get();
        Classes classe = classesRepo.findByNomClasse(classeSouhaitee).get();
        CandidatAdmission candidat = candidatAdmissionRepo.findByNomPrenomDateNaissance(nom, prenom, null)
                .orElseGet(() -> {
                    CandidatAdmission nouveauCandidat = new CandidatAdmission();
                    nouveauCandidat.setNom(nom);
                    nouveauCandidat.setPrenom(prenom);
                    nouveauCandidat.setEmail(email);
                    nouveauCandidat.setTelephone(telephone);
                    nouveauCandidat.setAdresse(adresse);
                    return candidatAdmissionRepo.save(nouveauCandidat);
                });

        // Création de la clé composite pour la demande d'admission
        DemandeAdmissionKey admissionKey = new DemandeAdmissionKey();
        admissionKey.setCandidatId(candidat.getId());
        admissionKey.setClasseId(classe.getId());
        admissionKey.setAnneeScolaireId(annee.getId());

        // Verifier si la demande n'existait pas
        if (demandeAdmissionRepo.existsById(admissionKey)) {
            throw new RuntimeException("Une demande d'admission existe deja pour ce candidat");
        }

        // Création de la demande d'admission
        DemandeAdmissions demandeAdmission = new DemandeAdmissions();
        demandeAdmission.setAdmissionId(admissionKey);
        demandeAdmission.setClasseSouhaitee(classe);
        demandeAdmission.setCandidatAdmission(candidat);
        demandeAdmission.setClasseSouhaitee(classe);
        demandeAdmission.setAnneeScolaire(annee);
        return demandeAdmissionRepo.save(demandeAdmission);
    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParAnnee() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerDemandesAdmissionsParAnnee'");
    }

    @Override
    public List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listerDemandesAdmissionsParEleve'");
    }

    @Override
    public void supprimerDemandeAdmission(Long eleveId, String anneeScolaire) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerDemandeAdmission'");
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
    public DemandeAdmissions creerDemandeAdmission(CandidatAdmission candidat, long idClasse, long idAnneeScolaire) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'creerDemandeAdmission'");
    }

}
