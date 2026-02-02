package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.Classes;

public interface ClassesSerices {

    Classes createClasse(Classes classe);

    Classes getClasseByNom(String nomClasse);

    Classes updateClasse(Long id, Classes classeDetails);

    void deleteClasse(Long id);

    List<Classes> getAllClasses();

}
