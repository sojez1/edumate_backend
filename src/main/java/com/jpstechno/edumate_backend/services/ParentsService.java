package com.jpstechno.edumate_backend.services;

import com.jpstechno.edumate_backend.modeles.Admissions;
import com.jpstechno.edumate_backend.modeles.Eleves;

public interface ParentsService {

    void signalerAbsence(Eleves eleve);

    Admissions demanderAdmission(Eleves eleve);

}
