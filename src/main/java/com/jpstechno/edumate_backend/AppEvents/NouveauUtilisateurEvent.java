package com.jpstechno.edumate_backend.AppEvents;

import org.springframework.context.ApplicationEvent;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NouveauUtilisateurEvent extends ApplicationEvent {

    private Utilisateurs utilisateur;
    private String urlForActivation;

    public NouveauUtilisateurEvent(Object source, Utilisateurs utilisateur, String url) {
        super(source);
        this.utilisateur = utilisateur;
        this.urlForActivation = url;
    }

}
