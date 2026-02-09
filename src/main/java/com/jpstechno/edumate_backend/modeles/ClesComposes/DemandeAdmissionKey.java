package com.jpstechno.edumate_backend.modeles.ClesComposes;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DemandeAdmissionKey implements Serializable {

    private Long candidatId;
    private Long classeId;
    private Long anneeScolaireId;
}
