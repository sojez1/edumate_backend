package com.jpstechno.edumate_backend.modeles.dto;

public record GetAdmissionDetailsForm(

        String dateNaissance,
        String anneeScolaire,
        String numeroDemande,
        String demandeur,
        String classeSollicitee,
        String statut,
        String dateDemande) {
}
