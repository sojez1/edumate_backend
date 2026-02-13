package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDate;
import java.util.List;

import com.jpstechno.edumate_backend.modeles.ClesComposes.DemandeAdmissionKey;
import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeAdmissions {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private DemandeAdmissionKey admissionId; /*
                                              * cle compose de candidat_id, classe_id et anneeScolaire_id
                                              * pour assurer une seule demande d'admission par candidat chaque annee.
                                              */

    private LocalDate dateDemandeAdmission = LocalDate.now();

    @Column(nullable = false, unique = true)
    private String numeroDemande;

    @Enumerated(EnumType.STRING)
    private StatutDemandeAdmission statutDemande = StatutDemandeAdmission.EN_ATTENTE;

    private String motivation;

    @ManyToOne
    @MapsId("candidatId")
    @JoinColumn(name = "candidat_id")
    private CandidatAdmission candidatAdmission;

    @ManyToOne
    @MapsId("classeId")
    @JoinColumn(name = "classe_id")
    private Classes classeSouhaitee;

    @ManyToOne
    @MapsId("anneeScolaireId")
    @JoinColumn(name = "annee_scolaire_id")
    private AnneeScolaires anneeScolaire;

    @OneToMany(mappedBy = "documentAdmission")
    private List<DocumentsJoints> documentsJoint;

};
