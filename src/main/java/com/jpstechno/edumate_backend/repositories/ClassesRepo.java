package com.jpstechno.edumate_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.Classes;

public interface ClassesRepo extends JpaRepository<Classes, Long> {
    Optional<Classes> findByNomClasse(String nomClasse);
}
