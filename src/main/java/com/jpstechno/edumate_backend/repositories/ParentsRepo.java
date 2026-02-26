package com.jpstechno.edumate_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.Parents;

public interface ParentsRepo extends JpaRepository<Parents, Long> {
    Optional<Parents> findByCourriel(String courriel);

}
