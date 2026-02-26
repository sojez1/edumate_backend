package com.jpstechno.edumate_backend.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.enumerations.TypeParents;

public interface CandidatAdmissionRepo extends JpaRepository<CandidatAdmission, Long> {

    Optional<CandidatAdmission> findByEmailIgnoreCase(String email);

    @Query("SELECT candidat FROM CandidatAdmission candidat WHERE candidat.nom = :nom AND candidat.prenom = :prenom AND candidat.dateNaissance = :dateNaissance")
    Optional<CandidatAdmission> getCandidatByNomCompletDateNaissance(@Param("nom") String nom,
            @Param("prenom") String prenom,
            @Param("dateNaissance") LocalDate DateNaissance);

}
