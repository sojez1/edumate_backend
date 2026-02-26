package com.jpstechno.edumate_backend.repositories;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;

import java.util.Optional;

public interface DemandeAdmissionRepo extends JpaRepository<DemandeAdmissions, String> {

    Optional<DemandeAdmissions> findByNumeroDemande(String numeroDemande);

    @Query("SELECT adm FROM DemandeAdmissions adm WHERE adm.anneeScolaire = :anneeScolaire AND adm.classeSouhaitee = :classe AND adm.candidatAdmission = :candidat")
    Optional<DemandeAdmissions> findByCandidatAnneeClasse(@Param("candidat") CandidatAdmission candidat,
            @Param("anneeScolaire") AnneeScolaires annee, @Param("classe") Classes clas);

}
