package com.jpstechno.edumate_backend.services;

import java.time.LocalDate;
import java.util.List;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.dto.DemandeAdmissionForm;

public interface DemandeAdmissionService {

    DemandeAdmissions creerDemandeAdmission(DemandeAdmissionForm admissionData);

    List<DemandeAdmissions> listerDemandeAdmission();

    List<DemandeAdmissions> listerDemandesAdmissionsParAnnee();

    List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId);

    void supprimerDemandeAdmission(Long eleveId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParClasseEtAnnee(Long classeId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat);

}
