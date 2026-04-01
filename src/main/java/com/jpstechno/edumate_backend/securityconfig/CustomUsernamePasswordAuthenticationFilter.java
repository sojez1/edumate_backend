package com.jpstechno.edumate_backend.securityconfig;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpstechno.edumate_backend.exceptions.AuthenticationException;
import com.jpstechno.edumate_backend.modeles.dto.LoginRequestDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authManager;
    private final OtpService otpService;
    private final TokenTemporaireService tokenTemporaire;
    private final ObjectMapper objectMapper;

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authManager, OtpService otpService,
            TokenTemporaireService tokenTemporaire, ObjectMapper objectMapper) {
        this.authManager = authManager;
        this.otpService = otpService;
        this.tokenTemporaire = tokenTemporaire;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/auth/login");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        try {

            // convert JSON data in request into Java Object
            final LoginRequestDto req = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);

            if (req.getUsername() == null || req.getPassword() == null) {
                throw new RuntimeException("username ou password manquant");
            }

            return authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        } catch (IOException ex) {
            throw new AuthenticationException("demande de login incorrect " + ex.getMessage());
        }

    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException {
        String username = auth.getName();

        // generer et envoyer le code OTP
        String otp = otpService.generateOTP(username);
        otpService.sendOtp(username, otp);

        // generer le token temporaire
        String tokenTemp = tokenTemporaire.create(username);

        // Mettre le token temporaire dans la reponse
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(Map.of("tempToken", tokenTemp)));

    }

}
