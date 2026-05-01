package com.jpstechno.edumate_backend.securityconfig;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

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
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // private final MyUserDetailsService userDetailsService;
    private final ApplicationContext context;

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        /*
         * /
         * String chemin = request.getServletPath();
         * if (chemin.equals("/edumate/utilisateurs/new_user") ||
         * chemin.startsWith("/edumate/auth")) {
         * filterChain.doFilter(request, response);
         * return;
         * }
         */

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
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);

            if (jwtService.isTokenValide(token, userDetails)) {
                // get roles from token
                List<RoleUtilisateurs> roles = jwtService.getUserRoleFromToken(token);

                // get the list of grantedAuthorities
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                logger.info(userName);

                System.out.println(" liste authorities est: "
                        + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

            }

        }
        filterChain.doFilter(request, response);

    }

}
