package com.jpstechno.edumate_backend.securityconfig;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpstechno.edumate_backend.modeles.dto.OtpRequestDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Ce filtre OTP assure l'authentification multi-facteur (2FA)
 * Est utiliser avec le filtre Username and Password filtre
 */
public class OtpFilter extends OncePerRequestFilter {

    private final TokenTemporaireService tokenTemporaireService;
    private final OtpService otpService;
    private final ObjectMapper objectMapper;

    public OtpFilter(TokenTemporaireService tokenTemporaireService, OtpService otpService, ObjectMapper objectMapper) {
        super();
        this.tokenTemporaireService = tokenTemporaireService;
        this.otpService = otpService;
        this.objectMapper = objectMapper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // skip the otp filter if the url is not auth/verifier-otp
        if (!request.getRequestURI().equals("/auth/verifier-otp")) {
            filterChain.doFilter(request, response);
            return;
        }

        OtpRequestDto req = objectMapper.readValue(request.getInputStream(), OtpRequestDto.class);

        // recuperer le username (courriel) a partir du token temporaire
        String username = tokenTemporaireService.validate(req.getTokenTemporaire());

        if (username == null || !otpService.isOtpValide(username, req.getOtp())) {
            response.setStatus(401);
            return;

        }

        // String jwtToken = jwtService.generateToken(username);
        String tempToken = tokenTemporaireService.create(username);

        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(Map.of("tempToken", tempToken)));
        filterChain.doFilter(request, response);

    }

}
