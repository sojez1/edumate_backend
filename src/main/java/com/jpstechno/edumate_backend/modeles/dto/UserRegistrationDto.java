package com.jpstechno.edumate_backend.modeles.dto;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.UtilisateursType;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegistrationDto {

    private String username;
    private String nom;
    private String prenoms;
    private String email;
    private String telephone;
    private String password;
    private List<RoleUtilisateurs> role;

}
