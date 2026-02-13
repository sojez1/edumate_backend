package com.jpstechno.edumate_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;

public interface CandidatAdmissionRepo extends JpaRepository<CandidatAdmission, Long> {

    Optional<CandidatAdmission> findByEmailIgnoreCase(String email);

}
