package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.repositories.ClassesRepo;
import com.jpstechno.edumate_backend.services.ClassesSerices;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClasseImplementation implements ClassesSerices {

    private final ClassesRepo classesRepo;

    @Override
    public Classes createClasse(Classes classe) {
        return classesRepo.save(classe);
    }

    @Override
    public Classes getClasseByNom(String nomClasse) {
        return classesRepo.findByNomClasse(nomClasse)
                .orElseThrow(() -> new RuntimeException("Classe not found with name: " + nomClasse));
    }

    @Override
    public Classes updateClasse(Long id, Classes classeDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClasse'");
    }

    @Override
    public void deleteClasse(Long id) {

    }

    @Override
    public List<Classes> getAllClasses() {
        Sort trieParNomClasse = Sort.by(Sort.Direction.ASC, "nomClasse");
        return classesRepo.findAll(trieParNomClasse);
    }

}
