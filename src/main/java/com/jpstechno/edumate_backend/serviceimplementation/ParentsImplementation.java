package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.CandidatAdmission;
import com.jpstechno.edumate_backend.modeles.Eleves;
import com.jpstechno.edumate_backend.modeles.Parents;
import com.jpstechno.edumate_backend.repositories.ParentsRepo;
import com.jpstechno.edumate_backend.services.ParentsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentsImplementation implements ParentsService {

    private final ParentsRepo parentRepo;

    @Override
    public void signalerAbsence(Eleves eleve) {

    }

    @Override
    public Parents ajouterParents(Parents parent) {
        return parentRepo.save(parent);
    }

    @Override
    public Parents modifierParents(long id, Parents parentsData) {
        Parents updatedParent = parentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Le parent que vous chercher a modifier n'existe pas"));
        updatedParent.setNom(parentsData.getNom());
        updatedParent.setPrenoms(parentsData.getPrenoms());
        updatedParent.setCourriel(parentsData.getCourriel());
        updatedParent.setTypeParent(parentsData.getTypeParent());
        updatedParent.setNumeroTelephone(parentsData.getNumeroTelephone());
        return parentRepo.save(updatedParent);
    }

    @Override
    public void supprimerParents(long id) {
        parentRepo.deleteById(id);
    }

    @Override
    public List<Parents> getListeDeTousLesParents() {
        Sort trieParNom = Sort.by(Sort.Direction.ASC, "nom");
        return parentRepo.findAll(trieParNom);
    }

    @Override
    public List<Parents> getListeParentsDuCandidat(CandidatAdmission candidat) {
        return null;
    }

    @Override
    public List<Parents> getListeParentsEtudiantActifs(Eleves etudiantActif) {
        return null;
    }

}
