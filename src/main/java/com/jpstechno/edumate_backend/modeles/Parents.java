package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.edumate_backend.modeles.enumerations.TypeParents;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nom;

    @Column(length = 50)
    private String prenoms;

    @Enumerated(EnumType.STRING)
    private TypeParents typeParent;

    @Column(length = 14)
    private String numeroTelephone;

    @NaturalId(mutable = true)
    private String courriel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CandidatAdmission candidat;

}
