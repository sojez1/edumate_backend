package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDate;
import java.util.List;

import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "ku_candidat_classe_annee", columnNames = {
                "candidat_id", "classe_id", "annees_scolaire_id"
        })
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeAdmissions {

    @Id
    private String numeroDemande;

    private LocalDate dateDemandeAdmission = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private StatutDemandeAdmission statutDemande = StatutDemandeAdmission.EN_ATTENTE;

    @Column(columnDefinition = "TEXT")
    private String motivation;

    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private CandidatAdmission candidatAdmission;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classes classeSouhaitee;

    @ManyToOne
    @JoinColumn(name = "annees_scolaire_id", nullable = false)
    private AnneeScolaires anneeScolaire;

    @OneToMany(mappedBy = "demandeAdmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentsJoints> documentsJoint;

    @OneToMany(mappedBy = "demandeAdmission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdmissionDecisions> decisions;

    private Boolean vieAvecLesDeuxParents = true;

}
