package com.jpstechno.edumate_backend.KeyGenerator;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;

public interface CustomIdGeneratorRepo extends JpaRepository<CustomIdGenerator, CustomIdEmbeddableKey> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // gestion acces concurente (empeche deux acces simultanee en ecriture)
    Optional<CustomIdGenerator> findById(CustomIdEmbeddableKey id);

}
