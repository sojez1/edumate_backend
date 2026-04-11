package com.jpstechno.edumate_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;

public interface HistoriqueClasseRepo extends JpaRepository<HistoriqueClassesEtudiant, Long> {

}
