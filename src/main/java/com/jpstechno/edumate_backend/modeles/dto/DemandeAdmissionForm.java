package com.jpstechno.edumate_backend.modeles.dto;

import java.time.LocalDate;
import java.util.List;

import com.jpstechno.edumate_backend.modeles.DocumentsJoints;

import lombok.Data;

@Data
public class DemandeAdmissionForm {

    private String nom;
    private String prenom;
    private String sexe;
    private String dateNaissance;
    private String email;
    private String numeroTelephone;
    private String adresse;
    private String classeSouhaitee;
    private String anneeScolaire;
    private String motivation;

    private List<DocumentsJoints> listeDocs;
}
