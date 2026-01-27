package com.jpstechno.edumate_backend.services;

import com.jpstechno.edumate_backend.modeles.TokenUtilisateurs;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;

public interface TokenService {

    String genererToken();

    String generateTokenValidationUrl(TokenUtilisateurs tokenUtilisateur);

    void emailTokenValidationLink(Utilisateurs utilisateur, String urlvalidation);

    String verifierToken(Long id, String token);

    String renvoyerToken(Long userId);

}
