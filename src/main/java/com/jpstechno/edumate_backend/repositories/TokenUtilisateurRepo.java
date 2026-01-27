package com.jpstechno.edumate_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jpstechno.edumate_backend.modeles.TokenUtilisateurs;

@Repository
public interface TokenUtilisateurRepo extends JpaRepository<TokenUtilisateurs, Long> {

    @Query("select tkn from TokenUtilisateurs tkn where tkn.id=:id and tkn.token=:token")
    Optional<TokenUtilisateurs> getUserToken(long id, String token);

}
