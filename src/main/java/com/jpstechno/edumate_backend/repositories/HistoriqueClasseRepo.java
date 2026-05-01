package com.jpstechno.edumate_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.modeles.Etudiants;
import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;

public interface HistoriqueClasseRepo extends JpaRepository<HistoriqueClassesEtudiant, Long> {

    List<HistoriqueClassesEtudiant> findByEtudiant(Etudiants etudiant);

    List<HistoriqueClassesEtudiant> findByAnneeScolaireAndClasse(AnneeScolaires annee, Classes classe);

}
