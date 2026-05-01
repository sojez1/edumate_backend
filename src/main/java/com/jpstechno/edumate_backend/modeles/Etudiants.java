package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("ETUDIANT")
public class Etudiants extends Utilisateurs {

    private String matricule;

    public Etudiants() {
        super();
        super.setRole(List.of(RoleUtilisateurs.ETUDIANT));
    }

    public Etudiants(String nom, String prenoms, String username, String email, String telephone, String password,
            String matricule) {
        super(nom, prenoms, username, email, telephone, password);
        super.setRole(List.of(RoleUtilisateurs.ETUDIANT));
        this.matricule = matricule;
    }

}
