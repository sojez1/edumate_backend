package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidatAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 75)
    private String prenom;

    @Column(nullable = false, length = 10)
    private String sexe;

    private LocalDate dateNaissance;

    @NaturalId(mutable = true)
    private String email;

    @Column(nullable = false, length = 20)
    private String numeroTelephone;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Parents> parents;

    @OneToMany(mappedBy = "candidatAdmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DemandeAdmissions> listeDesAdmissions;

}
