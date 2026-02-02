package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

public interface UtilisateurService {

    Utilisateurs ajouterUtilisateur(Utilisateurs utilisateur);

    Utilisateurs updateUtilisateurData(Utilisateurs newuserData, Long id);

    List<Utilisateurs> RechercherUtilisateur(String motCle);

    Utilisateurs desactiverUtilisateur(Long id);

    List<Utilisateurs> listeUtilisateur();

    void validerEmail(String token);

    void AjouterRole(RoleUtilisateurs role);

    Utilisateurs getUtilisateurByEmail(String email);

    Utilisateurs getUtilisateurById(Long id);

    boolean existsByEmail(String email);

    boolean hasRole(Utilisateurs utilisateur, RoleUtilisateurs role);

}
