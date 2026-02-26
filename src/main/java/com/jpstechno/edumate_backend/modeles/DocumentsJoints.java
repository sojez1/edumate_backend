package com.jpstechno.edumate_backend.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentsJoints {

    @Id
    @GeneratedValue
    private Long id;

    private String typeDoc;

    private String nomDuDocument;

    @Lob
    private byte[] documentData;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private DemandeAdmissions demandeAdmission;

}
