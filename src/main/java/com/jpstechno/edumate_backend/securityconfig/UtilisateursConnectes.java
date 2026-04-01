package com.jpstechno.edumate_backend.securityconfig;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;

public class UtilisateursConnectes implements UserDetails {

    private final Utilisateurs utilisateur;

    public UtilisateursConnectes(Utilisateurs utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return utilisateur.getRole() == null ? Collections.emptyList()
                : utilisateur.getRole().stream()
                        .map((role) -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return utilisateur.getPassword();
    }

    @Override
    public String getUsername() {
        return utilisateur.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return utilisateur.isActif();
    }

}
