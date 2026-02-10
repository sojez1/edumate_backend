package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpstechno.edumate_backend.modeles.ClesComposes.DemandeAdmissionKey;
import com.jpstechno.edumate_backend.modeles.enumerations.Genres;
import com.jpstechno.edumate_backend.modeles.enumerations.StatutDemandeAdmission;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    private DemandeAdmissionKey admissionId;

    private LocalDate dateDemandeAdmission = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private StatutDemandeAdmission statutDemande = StatutDemandeAdmission.EN_ATTENTE;

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
    private List<DocumentsSoumis> docsSoumisAdmission;

};
