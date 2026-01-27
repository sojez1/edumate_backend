package com.jpstechno.edumate_backend.serviceimplementation;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.AppEvents.NouveauUtilisateurEvent;
import com.jpstechno.edumate_backend.modeles.TokenUtilisateurs;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.repositories.TokenUtilisateurRepo;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;
import com.jpstechno.edumate_backend.services.TokenService;
import com.jpstechno.edumate_backend.utilitaires.MailCoursier;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImplementation implements TokenService {

    private final MailCoursier mailCoursier;
    private final TokenUtilisateurRepo tokenRepo;
    private final UtilisateurRepo userRepo;

    @Override
    public String verifierToken(Long id, String token) {
        Optional<TokenUtilisateurs> userToken = tokenRepo.getUserToken(id, token);
        if (userToken.isPresent() && userToken.get().getTokenExpirationTime().isBefore(LocalDateTime.now())) {
            // Mettre a jour l'utilisateur en mettant emailValide a true
            userRepo.updateValideEmail(id);
            return "valide";
        }
        return null;
    }

    @Override
    public String renvoyerToken(Long userId) {
        TokenUtilisateurs tokenToSave = null;
        Optional<Utilisateurs> tokenOwner = userRepo.findById(userId);

        if (tokenOwner.isPresent()) {
            String myToken = UUID.randomUUID().toString();
            tokenToSave = new TokenUtilisateurs(tokenOwner.get(), myToken);
            tokenRepo.save(tokenToSave); // ou modifier si existant

            // creer le lien a envoyer par message

            // envoyer le message par email a l'utilisateur

            return myToken;

        } else {
            return null;
        }

    }

    @Override
    public String genererToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateTokenValidationUrl(TokenUtilisateurs tokenUtilisateur) {
        String token = this.genererToken();
        String urlForActivation = "";
        // String urlValidation = evenementPlublier.getUrlForActivation() + "&token=" +
        // token;
        return null;
    }

    @Override
    public void emailTokenValidationLink(Utilisateurs utilisateur, String urlValidation) {
        String TextduMessage = String.format(
                "Merci pour votre inscription sur le site. Afin de valider votre email, veuillez cliquer sur le lien ci-dessous \n\n %s",
                urlValidation);

        String objetMessage = "Validation de votre courriel";

        mailCoursier.SendMail(utilisateur.getEmail(), TextduMessage,
                objetMessage);
    }

}
