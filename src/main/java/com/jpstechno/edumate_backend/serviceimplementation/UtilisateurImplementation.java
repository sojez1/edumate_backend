package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpstechno.edumate_backend.AppEvents.NouveauUtilisateurEvent;
import com.jpstechno.edumate_backend.modeles.TokenUtilisateurs;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;
import com.jpstechno.edumate_backend.repositories.TokenUtilisateurRepo;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;
import com.jpstechno.edumate_backend.services.UtilisateurService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurImplementation implements UtilisateurService {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepo userRepo;
    private final ApplicationEventPublisher eventPublisheur;
    private final TokenUtilisateurRepo tokenUtilisateurRepo;

    @Override
    public Utilisateurs ajouterUtilisateur(Utilisateurs utilisateur) {
        boolean emailExisted = userRepo.findByEmail(utilisateur.getEmail()).isPresent();

        if (!emailExisted) {
            // First, encrypted the password using passwordEncoder bean from configuration
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));

            Utilisateurs savedUser = userRepo.save(utilisateur);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/utilisateurs/verifierEmail")
                    .queryParam("userId", savedUser.getId())
                    .toUriString();

            // publish this event before the return to generate email validation token
            NouveauUtilisateurEvent nouvoUser = new NouveauUtilisateurEvent(this, savedUser, url);
            eventPublisheur.publishEvent(nouvoUser);
            return savedUser;
        } else {
            throw new RuntimeException("Cet email existe deja");
        }

    }

    /**
     * Permet d'activer ou de desactiver un utilisateur
     * Utilisateur desactiver ne peut pas login.
     * 
     * @param id id de l'utilisateur
     * @return
     */
    @Override
    public Utilisateurs activateOrDesactivateUilisateur(Long id) {
        Utilisateurs usr = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user id not valide"));
        usr.setActif(!usr.getActif());
        return userRepo.save(usr);
    }

    @Override
    public Utilisateurs updateUtilisateurData(Utilisateurs newuserData, Long id) {
        Utilisateurs user = userRepo.findById(id).get();
        user.setNom(newuserData.getNom());
        user.setPrenoms(newuserData.getPrenoms());
        user.setTelephone(newuserData.getTelephone());
        return userRepo.save(user);
    }

    @Override
    public List<Utilisateurs> RechercherUtilisateur(String motCle) {
        return this.listeUtilisateur().stream()
                .filter((util) -> util.getNom().equalsIgnoreCase(motCle))
                .toList();

    }

    @Override
    public List<Utilisateurs> listeUtilisateur() {
        Sort trieParNom = Sort.by(Sort.Direction.ASC, "nom");
        return userRepo.findAll(trieParNom);
    }

    public String generateUrlForEmailValidation(Utilisateurs utilisateur) {
        return null;
    }

    @Override
    public void validerEmail(String token) {

    }

    /**
     * Cette fonction permet d'ajouter un role a un utilisateur
     * 
     * @param user utilisateur a qui ont veut ajouter un role
     * @param role role que l'on veut ajouter a l'utilisateur
     */
    @Override
    public void AjouterRole(Utilisateurs user, RoleUtilisateurs role) {
        List<RoleUtilisateurs> theRoles = user.getRole();
        theRoles.add(role);
        user.setRole(theRoles);
        userRepo.save(user); // update in the database

    }

    @Override
    public Utilisateurs getUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public Utilisateurs getUtilisateurById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public boolean hasRole(Utilisateurs utilisateur, RoleUtilisateurs role) {
        return true;
    }

    @Override
    public void forgetPassword(String usernameOrPassword) {
        Optional<Utilisateurs> utilisateur = userRepo.getUserByUsernameOrEmail(usernameOrPassword);
        if (utilisateur.isPresent()) {// send link by email to change password
            // 1- creer le token
            String token = UUID.randomUUID().toString();
            // 2- Enregistrer le token dans la base de donnees
            TokenUtilisateurs userToken = new TokenUtilisateurs(utilisateur.get(), token);
            tokenUtilisateurRepo.save(userToken);
            // 3- creer url comportant le token et le username

        } else {
            throw new RuntimeException("Pas d<utilisateur corres[popndant aux donnees saisies");
        }

    }

}
