package com.jpstechno.edumate_backend.modeles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cette classe est utilisee pour modeliser les donnees des anciens ettudiants.
 * Elle sert a enroller les anciens etudiants dans le systeme.
 * Ici, est considerer comme ancien etudiant, celui qui est deja aux etudes dans
 * lecole et qui y est encore.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OldStudentRegistrationData {

    private Etudiants etudiant;
    private Classes classe;
    private AnneeScolaires anneeScolaire;

}
