package com.jpstechno.edumate_backend.modeles;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
public class Administrateurs extends Utilisateurs {
    private String fonction; // Secretaire, Censeur, Directeur, Comptable, ...
}
