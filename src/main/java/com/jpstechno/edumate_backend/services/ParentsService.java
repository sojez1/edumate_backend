package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Etudiants;
import com.jpstechno.edumate_backend.modeles.Parents;

public interface ParentsService {

    void signalerAbsence(Etudiants eleve);

    Parents ajouterParents(Parents parent);

    Parents modifierParents(long id, Parents parentsData);

    void supprimerParents(long id);

    List<Parents> getListeDeTousLesParents();

    List<Parents> getListeParentsDuCandidat(CandidatAdmission candidat);

    List<Parents> getListeParentsEtudiantActifs(Etudiants etudiantActif);

}
