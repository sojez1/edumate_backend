package com.jpstechno.edumate_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.edumate_backend.modeles.DocumentsJoints;

public interface DocumentsJointsRepo extends JpaRepository<DocumentsJoints, Long> {

    @Query("SELECT doc FROM DocumentsJoints doc WHERE doc.documentAdmission.numeroDemande=:numeroDemande")
    List<DocumentsJoints> findDocumentsByNumeroAdmission(@Param("numeroDemande") String numeroDemandeAdmission);

}
