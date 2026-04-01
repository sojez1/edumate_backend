package com.jpstechno.edumate_backend.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenTemporaire {

    @Id
    private Long id;

    private String token;

    private String username;

    private String expirationDate;

    private final long dureeTokenTemporaire = 5 * 60 * 1000;
}
