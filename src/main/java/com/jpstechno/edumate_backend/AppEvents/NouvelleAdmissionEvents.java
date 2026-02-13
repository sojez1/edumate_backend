package com.jpstechno.edumate_backend.AppEvents;

import java.time.Clock;

import org.springframework.context.ApplicationEvent;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NouvelleAdmissionEvents extends ApplicationEvent {

    private DemandeAdmissions laNouvelleDemande;

    public NouvelleAdmissionEvents(Object source, DemandeAdmissions nouvelleDemande) {
        super(source);
        this.laNouvelleDemande = nouvelleDemande;

    }

}
