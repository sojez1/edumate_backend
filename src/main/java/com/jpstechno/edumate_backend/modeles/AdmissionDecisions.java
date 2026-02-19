package com.jpstechno.edumate_backend.modeles;

import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmissionDecisions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private DemandeAdmissions demandeAdmission;

    @Enumerated(EnumType.STRING)
    private StatutDemandeAdmission statut;

    @Column(columnDefinition = "TEXT")
    private String commentaires;

}
