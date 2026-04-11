package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.OldStudentRegistrationData;
import com.jpstechno.edumate_backend.modeles.Parents;

public interface EtudiantService {

    List<Parents> getParents();

    String enrolerAncienEtudiant(OldStudentRegistrationData studentData);

}
