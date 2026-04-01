package com.jpstechno.edumate_backend.securityconfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UtilisateurRepo userRepo;

    public MyUserDetailsService(UtilisateurRepo userRepo) {
        this.userRepo = userRepo;

    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Utilisateurs utilisateur = userRepo.getUserByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or email"));
        return new UtilisateursConnectes(utilisateur);

    }

}
