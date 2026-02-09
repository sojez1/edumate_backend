package com.jpstechno.edumate_backend.services;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.Eleves;

public interface ParentsService {

    void signalerAbsence(Eleves eleve);

    DemandeAdmissions demanderAdmission(Eleves eleve);

}
