package com.jpstechno.edumate_backend.modeles;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TokenUtilisateurs {

    private static final long TOKEN_DURATION = 10; // duree de vie du token en minute

    @Id
    private Long id;

    private String token;

    private LocalDateTime tokenExpirationTime;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    @MapsId // id_utilisateur will be the same as the table id
    private Utilisateurs utilisateur;

    public TokenUtilisateurs(Utilisateurs user, String token) {
        this.token = token;
        this.utilisateur = user;
        this.tokenExpirationTime = LocalDateTime.now().plusMinutes(TOKEN_DURATION);

    }

}
