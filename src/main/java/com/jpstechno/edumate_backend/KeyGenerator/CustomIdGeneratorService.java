package com.jpstechno.edumate_backend.KeyGenerator;

import java.time.Year;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomIdGeneratorService {

    private final CustomIdGeneratorRepo generateurRepo;

    public String nextId(String prefixe) {

        int annee = Year.now().getValue() % 100; // recupere l'annee courante sur 2 digits

        CustomIdEmbeddableKey prefixeAnnee = new CustomIdEmbeddableKey(prefixe, annee);

        CustomIdGenerator generatedKey = generateurRepo.findById(prefixeAnnee).orElseGet(
                () -> {
                    CustomIdGenerator newYearId = new CustomIdGenerator();
                    newYearId.setId(prefixeAnnee);
                    newYearId.setValue(0L);
                    return newYearId;

                });

        long nextSequence = generatedKey.getValue() + 1;
        generatedKey.setValue(nextSequence);
        generateurRepo.save(generatedKey);

        return String.format("%s%02d%09d", prefixe, annee, nextSequence);

    }
}
