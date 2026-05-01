package com.jpstechno.edumate_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import jakarta.transaction.Transactional;

public interface UtilisateurRepo extends JpaRepository<Utilisateurs, Long> {

    Optional<Utilisateurs> findByEmail(String email);

    @Query("SELECT user FROM Utilisateurs user WHERE LOWER(user.username) = LOWER(:usernameOrEmail) OR LOWER(user.email) = LOWER(:usernameOrEmail)")
    Optional<Utilisateurs> getUserByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    @Transactional
    @Modifying
    @Query("update Utilisateurs usr set usr.valideEmail=true where usr.id=:id")
    void updateValideEmail(@Param("id") Long id);

    List<Utilisateurs> findByRole(RoleUtilisateurs role);

}
