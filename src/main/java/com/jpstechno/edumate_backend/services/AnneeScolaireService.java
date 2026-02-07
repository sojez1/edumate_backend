package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;

public interface AnneeScolaireService {
    AnneeScolaires creerAnneeScolaire(AnneeScolaires anneeScolaire);

    List<AnneeScolaires> listerAnneesScolaires();

    void supprimerAnneeScolaire(Long id);

    AnneeScolaires modifierAnneeScolaire(Long id, String nouvelleAnneeScolaire);

    AnneeScolaires activerOuDesactiverAnneeScolaire(Long id);

}
