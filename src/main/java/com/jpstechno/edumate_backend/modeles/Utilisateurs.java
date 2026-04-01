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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String username; // id for login

    private String nom;

    @Column(nullable = false)
    private String prenoms;

    @NaturalId(mutable = true)
    private String email;

    private boolean valideEmail = false;

    private boolean actif = false;

    private String telephone;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = RoleUtilisateurs.class, fetch = FetchType.EAGER)
    @Column(name = "userroles")
    private List<RoleUtilisateurs> role;
}
