package com.jpstechno.edumate_backend.securityconfig;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Ce filtre est utilise chaque fois qu'une requete est envoyee au serveur
 * Il sert a verifier la validite du token.
 * Si token pas valide, retourne l'utilisateur a l'authentification par username
 * et password
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MyUserDetailsService userDetailsService;
    // private final ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String chemin = request.getServletPath();
        if (chemin.equals("/edumate/utilisateurs/new_user") || chemin.startsWith("/edumate/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authenticationHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        // verifier si authenticationHeader existe et recuperer le token ainsi que le
        // username
        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            token = authenticationHeader.substring(7);
            userName = jwtService.getUsernameFromToken(token);
        }

        // verifier la validiter du token
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // UserDetails userDetails =
            // context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (jwtService.isTokenValide(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }

        }
        filterChain.doFilter(request, response);

    }

}
