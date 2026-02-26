package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class AnneeScolaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId(mutable = true)
    @Column(unique = true, nullable = false, length = 9)
    private String anneeScolaire; // format: "2023-2024"

    private boolean active = false; // indique si l'année scolaire est active

    @OneToMany(mappedBy = "anneeScolaire")
    @JsonIgnore
    private List<DemandeAdmissions> listeAnneeScolaire;

}
