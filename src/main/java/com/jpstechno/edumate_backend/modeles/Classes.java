package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.edumate_backend.modeles.enumerations.OrdreEnseignements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId(mutable = true)
    @Column(unique = true, nullable = false, length = 10)
    private String nomClasse; // appelation de la classe (ex: 6ème, Terminale, etc.)

    @Column(nullable = false, length = 50)
    private String appelation; // appelation longue de la classe (ex: Sixième, Terminale, etc.)

    @Enumerated(EnumType.STRING)
    private OrdreEnseignements ordreEnseignement; // maternelle, primaire, secondaire, superieur

    @OneToMany(mappedBy = "classeActuelle")
    private List<CandidatAdmission> candidatsAdmission; // liste de ceux qui ont fait demande d'admission dans cette
                                                        // classe
}
