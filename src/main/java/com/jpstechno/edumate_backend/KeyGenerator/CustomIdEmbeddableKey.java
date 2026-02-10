package com.jpstechno.edumate_backend.KeyGenerator;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CustomIdEmbeddableKey implements Serializable {

    private String prefixe;
    private Integer annee;

}
