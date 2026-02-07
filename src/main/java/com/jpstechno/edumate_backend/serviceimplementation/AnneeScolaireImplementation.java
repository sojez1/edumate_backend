package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.repositories.AnneeScolaireRepo;
import com.jpstechno.edumate_backend.services.AnneeScolaireService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnneeScolaireImplementation implements AnneeScolaireService {

    private final AnneeScolaireRepo anneeScolaireRepo;

    @Override
    public AnneeScolaires creerAnneeScolaire(AnneeScolaires anneeScolaire) {
        return anneeScolaireRepo.save(anneeScolaire);
    }

    @Override
    public List<AnneeScolaires> listerAnneesScolaires() {
        Sort trieAnnee = Sort.by(Sort.Direction.DESC, "anneeScolaire");
        return anneeScolaireRepo.findAll(trieAnnee);
    }

    @Override
    public void supprimerAnneeScolaire(Long id) {
        anneeScolaireRepo.deleteById(id);
    }

    @Override
    public AnneeScolaires modifierAnneeScolaire(Long id, String nouvelleAnneeScolaire) {
        AnneeScolaires anneeScolaire = anneeScolaireRepo.findById(id).orElse(null);
        if (anneeScolaire != null) {
            anneeScolaire.setAnneeScolaire(nouvelleAnneeScolaire);
            return anneeScolaireRepo.save(anneeScolaire);
        }
        throw new RuntimeException("Annee scolaire non trouvée");
    }

    @Override
    public AnneeScolaires activerOuDesactiverAnneeScolaire(Long id) {
        AnneeScolaires anneeScolaire = anneeScolaireRepo.findById(id).orElse(null);
        if (anneeScolaire != null) {
            anneeScolaire.setActive(!anneeScolaire.isActive());
            return anneeScolaireRepo.save(anneeScolaire);
        }
        throw new RuntimeException("Annee scolaire non trouvée");
    }

}
