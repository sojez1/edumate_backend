package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.dto.FiltreDemandeAdmission;

public interface DemandeAdmissionService {

    DemandeAdmissions creerDemandeAdmission(DemandeAdmissions admissionData);

    List<DemandeAdmissions> listeDemandeParAnneeClasseStatut(FiltreDemandeAdmission filtre);

    List<DemandeAdmissions> listerDemandeAdmission();

    List<DemandeAdmissions> listerDemandesAdmissionsParAnnee();

    List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId);

    void supprimerDemandeAdmission(Long eleveId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParClasseEtAnnee(Long classeId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat);

}
