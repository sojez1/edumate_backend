package com.jpstechno.edumate_backend.DaoUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jpstechno.edumate_backend.modeles.AnneeScolaires;
import com.jpstechno.edumate_backend.repositories.AnneeScolaireRepo;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@RequiredArgsConstructor
@DisplayName("Tests Unitaires pour le DAO AnneeScolaire")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnneeScolaireDaoTest {

    private final AnneeScolaireRepo anneeScolaireRepo;

    @Test
    @Order(1)
    @DisplayName("Test de l'insertion de l'annee scolaire 2025-2026 dans la base de données")
    @Rollback(false)
    public void testAnneeScolaireDao() {
        // creation nouvelle annee scolaire
        AnneeScolaires annee2025 = new AnneeScolaires();
        annee2025.setAnneeScolaire("2025-2026");

        // insertion dans la base de données
        AnneeScolaires savedAnnee = anneeScolaireRepo.save(annee2025);

        // vérification de l'insertion
        assertNotNull(savedAnnee);
        assertEquals("2025-2026", savedAnnee.getAnneeScolaire());
    }

}
