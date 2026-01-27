package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpstechno.edumate_backend.modeles.enumerations.Genres;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Admissions {

    private Long id;

    private Utilisateurs candidat;

    private LocalDate dateDeNaissance;

    private Genres genre;

    private String nationalite;

    @ManyToOne
    @JoinColumn(name = "pere_id")
    @JsonIgnore
    private Utilisateurs pere;

    @ManyToOne
    @JoinColumn(name = "mere_id")
    @JsonIgnore
    private Utilisateurs mere;

    @ManyToOne
    @JoinColumn(name = "tuteur_id")
    @JsonIgnore
    private Utilisateurs tuteur;

};
