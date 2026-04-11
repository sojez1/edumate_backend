package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = jakarta.persistence.InheritanceType.TABLE_PER_CLASS)
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NaturalId
    protected String username; // id for login

    protected String nom;

    @Column(nullable = false)
    protected String prenoms;

    @NaturalId(mutable = true)
    protected String email;

    @Builder.Default
    protected boolean valideEmail = false;

    @Builder.Default
    protected boolean actif = false;

    protected String telephone;

    protected String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleUtilisateurs.class, fetch = FetchType.EAGER)
    @Column(name = "userroles")
    protected List<RoleUtilisateurs> role;
}
