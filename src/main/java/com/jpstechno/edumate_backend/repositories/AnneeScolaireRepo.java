package com.jpstechno.edumate_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import java.util.Optional;

public interface AnneeScolaireRepo extends JpaRepository<AnneeScolaires, Long> {

    Optional<AnneeScolaires> findByAnneeScolaire(String anneeScolaire);

}
