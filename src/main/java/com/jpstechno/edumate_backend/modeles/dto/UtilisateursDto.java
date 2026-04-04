package com.jpstechno.edumate_backend.modeles.dto;

import java.util.List;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette DTO utilisateur est utiliser pour recuperer les donnees d'un
 * utilisateur sans son mot de passe et autres informations sensibles; evitant
 * ainsi de les exposer.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateursDto {
    private Long id;
    private String username;
    private String nom;
    private String prenoms;
    private String email;
    private boolean valideEmail = false;
    private boolean actif = false;
    private String telephone;
    private List<RoleUtilisateurs> role;

}
