package com.jpstechno.edumate_backend.controlleurs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.services.TokenService;
import com.jpstechno.edumate_backend.services.UtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("utilisateurs")
@RequiredArgsConstructor
public class UtilisateurControlleur {

    private final UtilisateurService userService;
    private final TokenService tokenService;

    @GetMapping("all_users")
    public ResponseEntity<List<Utilisateurs>> getAllUsers() {
        List<Utilisateurs> myUsersList = userService.listeUtilisateur();
        return new ResponseEntity<List<Utilisateurs>>(myUsersList, HttpStatus.OK);
    }

    @PostMapping("new_user")
    public ResponseEntity<Utilisateurs> createUser(@RequestBody Utilisateurs utilisateur) {
        Utilisateurs createduser = userService.ajouterUtilisateur(utilisateur);
        return new ResponseEntity<Utilisateurs>(createduser, HttpStatus.CREATED);
    }

    @GetMapping("verifierEmail")
    public String verifierEmail(@RequestParam("userId") String userId, @RequestParam("token") String token) {
        String result = tokenService.verifierToken(Long.parseLong(userId), token);
        if (result.equals("valide")) {
            return "la validation de votre email est reussie";
        }
        return "Echec de la validation de votre email";

    }

}
