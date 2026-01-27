package com.jpstechno.edumate_backend.modeles;

import java.util.List;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private String nom;

    @Column(nullable = false)
    private String prenoms;

    @NaturalId(mutable = true)
    private String email;

    private boolean valideEmail = false;

    private String telephone;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Roles.class)
    @Column(name = "roles")
    private List<Roles> role;
}
