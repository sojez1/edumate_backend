package com.jpstechno.edumate_backend.utilitaires;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jpstechno.edumate_backend.modeles.Administrateurs;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Cette classe permet de creer un utilisateur admin par defaut au cas ou
 * celui-ci n'existerait pas dans la base de donnees
 * Utilisateur admin par defaut.
 * Doit etre supprimer apres creation du premier admin pour raison de securité
 */
@Configuration
public class DataInit {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepo utilisateurRepo;
    private static final Logger logger = LoggerFactory.getLogger(DataInit.class);

    public DataInit(UtilisateurRepo utilisateurRepo, PasswordEncoder passwordEncoder) {
        this.utilisateurRepo = utilisateurRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initData() {
        return args -> {
            // verifier si au moins un admin existe
            if (utilisateurRepo.findByRole(RoleUtilisateurs.ADMINISTRATION).isEmpty()) {
                Administrateurs admin = new Administrateurs();
                admin.setActif(true);
                admin.setEmail("admin@ecole.com");
                admin.setFonction("Systeme");
                admin.setNom("edumate");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setPrenoms("admin");
                admin.setRole(List.of(RoleUtilisateurs.ADMINISTRATION));
                admin.setTelephone("000000000");
                admin.setUsername("admin");
                admin.setValideEmail(true);
                utilisateurRepo.save(admin);
                logger.info("Admin bien cree");
            }
        };
    }

}
