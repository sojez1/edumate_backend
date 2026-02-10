package com.jpstechno.edumate_backend.KeyGenerator;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomIdGenerator {

    @EmbeddedId
    private CustomIdEmbeddableKey id;

    private Long value;

}
