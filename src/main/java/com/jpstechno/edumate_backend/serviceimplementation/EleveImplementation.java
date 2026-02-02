package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.Parents;
import com.jpstechno.edumate_backend.modeles.UtilisateursType;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;
import com.jpstechno.edumate_backend.services.ElevesService;

public class EleveImplementation implements ElevesService, UtilisateursType {

    @Override
    public RoleUtilisateurs getTypeUtilisateur() {
        return null;
    }

    @Override
    public List<Parents> getParents() {
        return null;
    }

}
