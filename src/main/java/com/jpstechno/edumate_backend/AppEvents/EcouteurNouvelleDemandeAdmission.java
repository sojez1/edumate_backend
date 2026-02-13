package com.jpstechno.edumate_backend.AppEvents;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.utilitaires.MailCoursier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EcouteurNouvelleDemandeAdmission {

    private final MailCoursier coursier;

    @Async
    @EventListener
    public void afterNouvelleAdmission(NouvelleAdmissionEvents admissionEvent) {
        DemandeAdmissions laNouvelleDemande = admissionEvent.getLaNouvelleDemande();
        CandidatAdmission candidat = laNouvelleDemande.getCandidatAdmission();

        String objetDuMessage = "Edumante - Reception de votre demande d'admission";
        String corpsDuMessage = "Votre de mande d'admission est recue sous le numero et est en cours de traitement. Nous vous revenons des qu'il y aurait du nouveau";

        // envoyer un mail au demandeur
        coursier.SendMail(candidat.getEmail(), corpsDuMessage, objetDuMessage);
    }

}
