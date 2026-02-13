package com.jpstechno.edumate_backend.services;

import java.time.LocalDate;
import java.util.List;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissionForm;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;

public interface DemandeAdmissionService {

    DemandeAdmissions creerDemandeAdmission(DemandeAdmissionForm admissionData);

    List<DemandeAdmissions> listerDemandesAdmissionsParAnnee();

    List<DemandeAdmissions> listerDemandesAdmissionsParEleve(Long eleveId);

    void supprimerDemandeAdmission(Long eleveId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParClasseEtAnnee(Long classeId, String anneeScolaire);

    List<DemandeAdmissions> listerDemandesAdmissionsParEtat(String etat);

}
