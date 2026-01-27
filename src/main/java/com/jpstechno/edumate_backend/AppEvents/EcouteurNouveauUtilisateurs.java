package com.jpstechno.edumate_backend.AppEvents;

import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.jpstechno.edumate_backend.modeles.TokenUtilisateurs;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.repositories.TokenUtilisateurRepo;
import com.jpstechno.edumate_backend.utilitaires.MailCoursier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class EcouteurNouveauUtilisateurs {

    private final TokenUtilisateurRepo tokenRepository;
    private final MailCoursier mailCoursier;

    @EventListener
    @Async
    public void onApplicationEvent(NouveauUtilisateurEvent evenementPlublier) {

        // recuperer l'utilisateur publier
        Utilisateurs utilisateurPublier = evenementPlublier.getUtilisateur();

        // generer le token
        String token = UUID.randomUUID().toString();

        // enregistrer le token dans la base de donnees
        TokenUtilisateurs userToken = new TokenUtilisateurs(utilisateurPublier, token);
        tokenRepository.save(userToken);

        // creer l'url de validation a envoyer a l'utilisateur par mail
        String urlValidation = evenementPlublier.getUrlForActivation() + "&token=" + token;

        // envoyer un email a l'utilisateur contenant url

        String TextduMessage = String.format(
                "Merci pour votre inscription sur le site. Afin de valider votre email, veuillez cliquer sur le lien ci-dessous \n\n %s",
                urlValidation);
        String objetMessage = "Validation de votre courriel";
        mailCoursier.SendMail(utilisateurPublier.getEmail(), TextduMessage,
                objetMessage);

    }

}
