package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;

public interface DemandeAdmissionService {

    DemandeAdmissions creerDemandeAdmission(DemandeAdmissions demandeAdmission);

    DemandeAdmissions creerDemandeAdmission(CandidatAdmission candidat, long idClasse, long idAnneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParAnnee();

    List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId);

    void supprimerDemandeAdmission(Long eleveId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParClasseEtAnnee(Long classeId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat);

}
