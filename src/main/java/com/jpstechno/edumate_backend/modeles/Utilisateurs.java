package com.jpstechno.edumate_backend.modeles;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
@DiscriminatorColumn(name = "UserType", discriminatorType = DiscriminatorType.STRING)
public abstract class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NaturalId
    private String username; // id for login

    private String nom;

    private String prenoms;

    @NaturalId(mutable = true)
    private String email;

    private boolean valideEmail = false;

    private Boolean actif = false;

    private String telephone;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleUtilisateurs.class, fetch = FetchType.EAGER)
    @Column(name = "userroles")
    private List<RoleUtilisateurs> role;

    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenoms, String username, String email,
            String telephone, String password) {
        this.username = username;
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.valideEmail = false;
        this.actif = false;
        this.role = new ArrayList<>();
    }

    public Utilisateurs(Long id, String nom, String prenoms, String username, String email,
            String telephone, String password) {
        this(nom, prenoms, username, email, telephone, password);
        this.id = id;
    }
}
