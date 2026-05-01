package com.jpstechno.edumate_backend.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jpstechno.edumate_backend.modeles.Etudiants;
import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;
import com.jpstechno.edumate_backend.modeles.OldStudentRegistrationData;
import com.jpstechno.edumate_backend.modeles.Parents;

public interface EtudiantService {

    List<Parents> getParents();

    /**
     * Permet d'enregistrer un ancien etudiant (encore etudiant dans l'ecole)
     * dans le nouveau systeme.
     * 
     * @param studentData donnees de l'etudiant a enregistrer
     * @return un text pour dire si l'enregistrement est bien passe ou non
     */
    String enrolerAncienEtudiant(OldStudentRegistrationData studentData);

    /**
     * Permet pour un administrateur ou le webmaster de recuperer
     * l'historique des classes effectuees par un etudiants
     * 
     * @param etudiant etudiant dont on veut connaitre l'historique des classes
     *                 annee par annee
     * @return liste de l'historique
     */
    ResponseEntity<List<HistoriqueClassesEtudiant>> getHistoriqueClasseEtudiant(Etudiants etudiant);

    /**
     * Permet pour un etudiant actuellement connecte
     * de recuperer son propre historique des classes effectuees
     * (etudiant, annee scolaire, classe)
     * 
     * @return
     */
    List<HistoriqueClassesEtudiant> getConnectedStudentData();

}
