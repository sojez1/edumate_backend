package com.jpstechno.edumate_backend.controlleurs;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.dto.UserRegistrationDto;
import com.jpstechno.edumate_backend.modeles.dto.UtilisateursDto;
import com.jpstechno.edumate_backend.services.TokenService;
import com.jpstechno.edumate_backend.services.UtilisateurService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurControlleur {

    private final UtilisateurService userService;
    private final TokenService tokenService;

    /**
     * Cette methode retourne la liste de tous les utilisateurs du systeme.
     * Elle est accessible uniquement au webmaster et aux membres de
     * l'administration de l'ecole
     * 
     * @return
     */
    @GetMapping("/all_users")
    @PreAuthorize("hasAnyRole('WEBMASTER','ADMINISTRATION')")
    public ResponseEntity<List<UtilisateursDto>> getAllUsers() {
        List<Utilisateurs> myUsersList = userService.listeUtilisateur();
        List<UtilisateursDto> usersDtos = myUsersList.stream()
                .map((user) -> {
                    UtilisateursDto dto = new UtilisateursDto();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setNom(user.getNom());
                    dto.setPrenoms(user.getPrenoms());
                    dto.setEmail(user.getEmail());
                    dto.setTelephone(user.getTelephone());
                    dto.setRole(user.getRole());
                    dto.setValideEmail(user.isValideEmail());
                    dto.setActif(user.getActif());
                    return dto;
                }).toList();
        return new ResponseEntity<List<UtilisateursDto>>(usersDtos, HttpStatus.OK);

    }

    @PostMapping("/new_user")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Utilisateurs> createUser(@RequestBody UserRegistrationDto utilisateur) {
        /*
         * /
         * Utilisateurs newUser = new Utilisateurs();
         * newUser.setUsername(utilisateur.getUsername());
         * newUser.setNom(utilisateur.getNom());
         * newUser.setPrenoms(utilisateur.getPrenoms());
         * newUser.setEmail(utilisateur.getEmail());
         * newUser.setTelephone(utilisateur.getTelephone());
         * newUser.setPassword(utilisateur.getPassword());
         * newUser.setRole(utilisateur.getRole());
         * newUser.setValideEmail(false);
         * newUser.setActif(false);
         * Utilisateurs createduser = userService.ajouterUtilisateur(newUser);
         * return new ResponseEntity<Utilisateurs>(createduser, HttpStatus.CREATED);
         */
        return null;
    }

    @GetMapping("verifierEmail")
    @PreAuthorize("permitAll()")
    public String verifierEmail(@RequestParam("userId") String userId, @RequestParam("token") String token) {
        String result = tokenService.verifierToken(Long.parseLong(userId), token);
        if (result.equals("valide")) {
            return "la validation de votre email est reussie";
        }
        return "Echec de la validation de votre email";

    }

    @PostMapping("/activer-desactiver")
    @PreAuthorize("hasAnyRole('WEBMASTER','ADMINISTRATION')")
    public ResponseEntity<Utilisateurs> activateOrDesactivateUser(@RequestParam("id") long id) {
        Utilisateurs user = userService.activateOrDesactivateUilisateur(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
