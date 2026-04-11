package com.jpstechno.edumate_backend.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cette classe représente l'historique des classes d'un étudiant. Elle contient
 * des informations sur les classes précédentes de l'étudiant, y compris l'année
 * scolaire associée à chaque classe.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class HistoriqueClassesEtudiant {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Etudiants etudiant;

    @ManyToOne
    private Classes classe;

    @ManyToOne
    private AnneeScolaires anneeScolaire;
}
