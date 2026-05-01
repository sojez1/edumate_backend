package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Administrateurs extends Utilisateurs {
    private String fonction; // Secretaire, Censeur, Directeur, Comptable, ...

    public Administrateurs() {
        super();
        super.setRole(List.of(RoleUtilisateurs.ADMINISTRATION));
    }

    public Administrateurs(String nom, String prenoms, String username, String email, String telephone, String password,
            String fonction) {
        super(nom, prenoms, username, email, telephone, password);
        super.setRole(List.of(RoleUtilisateurs.ADMINISTRATION));
        this.fonction = fonction;
    }

}
