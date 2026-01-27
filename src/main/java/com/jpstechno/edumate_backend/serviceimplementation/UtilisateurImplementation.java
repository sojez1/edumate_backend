package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpstechno.edumate_backend.AppEvents.NouveauUtilisateurEvent;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;
import com.jpstechno.edumate_backend.services.UtilisateurService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurImplementation implements UtilisateurService {

    private final UtilisateurRepo userRepo;
    // private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisheur;

    @Override
    public Utilisateurs ajouterUtilisateur(Utilisateurs utilisateur) {
        boolean emailExisted = userRepo.findByEmail(utilisateur.getEmail()).isPresent();

        if (!emailExisted) {
            // String cryptedPassword = passwordEncoder.encode(utilisateur.getPassword());
            // utilisateur.setPassword(cryptedPassword);
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

    @Override
    public Utilisateurs updateUtilisateurData(Utilisateurs newuserData, Long id) {
        Utilisateurs user = userRepo.findById(id).get();
        user.setEmail(newuserData.getEmail());
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
    public Utilisateurs desactiverUtilisateur(Long id) {
        return null;
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

}
