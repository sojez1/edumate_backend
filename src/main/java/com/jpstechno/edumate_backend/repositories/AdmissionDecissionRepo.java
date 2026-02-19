package com.jpstechno.edumate_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jpstechno.edumate_backend.modeles.AdmissionDecisions;

public interface AdmissionDecissionRepo extends JpaRepository<AdmissionDecisions, Long> {

    @Query("SELECT decision FROM AdmissionDecisions decision WHERE decision.demandeAdmission.numeroDemande = :numerodemande")
    List<AdmissionDecisions> findByNumeroDemande(String numeroDemande);

}
