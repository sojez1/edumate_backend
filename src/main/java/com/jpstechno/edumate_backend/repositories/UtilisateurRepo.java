package com.jpstechno.edumate_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;

import jakarta.transaction.Transactional;

public interface UtilisateurRepo extends JpaRepository<Utilisateurs, Long> {

    Optional<Utilisateurs> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update Utilisateurs usr set usr.valideEmail=true where usr.id=:id")
    void updateValideEmail(@Param("id") Long id);

}
