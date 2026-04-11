package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.Etudiants;
import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;
import com.jpstechno.edumate_backend.modeles.OldStudentRegistrationData;
import com.jpstechno.edumate_backend.modeles.Parents;
import com.jpstechno.edumate_backend.modeles.UtilisateursType;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;
import com.jpstechno.edumate_backend.repositories.EtudiantRepo;
import com.jpstechno.edumate_backend.repositories.HistoriqueClasseRepo;
import com.jpstechno.edumate_backend.services.EtudiantService;

import jakarta.transaction.Transactional;

@Service
public class EtudiantImplementation implements EtudiantService, UtilisateursType {

    @Autowired
    private EtudiantRepo etudiantRepo;
    private HistoriqueClasseRepo historiqueClasseRepo;

    @Override
    public RoleUtilisateurs getTypeUtilisateur() {
        return RoleUtilisateurs.ELEVE;
    }

    @Override
    public List<Parents> getParents() {
        return null;
    }

    @Override
    @Transactional
    public String enrolerAncienEtudiant(OldStudentRegistrationData studentData) {
        Etudiants student = etudiantRepo.save(studentData.getEtudiant());

        HistoriqueClassesEtudiant histoEtudiant = new HistoriqueClassesEtudiant();
        histoEtudiant.setEtudiant(student);
        histoEtudiant.setAnneeScolaire(studentData.getAnneeScolaire());
        histoEtudiant.setClasse(studentData.getClasse());
        historiqueClasseRepo.save(histoEtudiant);
        return "L'enrollement de l'etudoiant est fait avec succes. Attente de la validation de son compte par l'administration de l'ecole.";

    }

}
