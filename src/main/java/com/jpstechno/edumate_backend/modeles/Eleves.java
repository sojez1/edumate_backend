package com.jpstechno.edumate_backend.modeles;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

public class Eleves implements UtilisateursType {

    // private List<Parents> parents;

    @Override
    public RoleUtilisateurs getTypeUtilisateur() {
        return RoleUtilisateurs.ELEVE;
    }

}
