package com.jpstechno.edumate_backend.modeles;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.edumate_backend.modeles.enumerations.OrdreEnseignements;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String nomClasse; // appelation de la classe (ex: 6ème, Terminale, etc.)

    @Enumerated(EnumType.STRING)
    private OrdreEnseignements ordreEnseignement; // maternelle, primaire, secondaire, superieur
}
