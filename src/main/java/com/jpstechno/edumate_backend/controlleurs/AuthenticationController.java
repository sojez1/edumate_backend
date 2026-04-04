package com.jpstechno.edumate_backend.controlleurs;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.exceptions.AuthenticationException;
import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.dto.LoginRequestDto;
import com.jpstechno.edumate_backend.modeles.dto.OtpRequestDto;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;
import com.jpstechno.edumate_backend.securityconfig.JwtService;
import com.jpstechno.edumate_backend.securityconfig.MyUserDetailsService;
import com.jpstechno.edumate_backend.securityconfig.OtpService;
import com.jpstechno.edumate_backend.securityconfig.TokenTemporaireService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenTemporaireService tokenTemporireService;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final UtilisateurRepo utilisateurRepo;
    private final MyUserDetailsService myUserDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager,
            TokenTemporaireService tokenTemporireService, JwtService jwtService, OtpService otpService,
            UtilisateurRepo utilisateurRepo, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.tokenTemporireService = tokenTemporireService;
        this.jwtService = jwtService;
        this.otpService = otpService;
        this.utilisateurRepo = utilisateurRepo;
        this.myUserDetailsService = myUserDetailsService;

    }

    /**
     * Permet d'authentifier un utilisateur en se basant sur son nom d'utilisateur
     * et son mot de passe
     * 
     * @param user requete de login contenant username et password
     * @return un token temporaire qui serait utilise pour la verification OTP
     * @throws AuthenticationException si l'authentification echoue
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public String userLogin(@RequestBody LoginRequestDto user) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (auth.isAuthenticated()) {
            Utilisateurs utilisateur = utilisateurRepo.getUserByUsernameOrEmail(user.getUsername()).get();
            return jwtService.generateAccessToken(utilisateur);
        } else {
            throw new AuthenticationException("Identifiants invalides");
        }

    }

    public String forgetPassword(String usernameOrPassword) {
        return null;
    }

    /**
     * Verifier le code OTP et le token temporaire puis renvoie un jwt si valide
     * 
     * @param otpRequest requete comportant le code OTP et le token
     * @return
     */
    @PostMapping("verifier-otp")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> verifierOtp(@RequestBody OtpRequestDto otpRequest) {

        String username = tokenTemporireService.validate(otpRequest.getTokenTemporaire());

        if (username == null || otpService.isOtpValide(username, otpRequest.getOtp())) {
            return ResponseEntity.status(401).body(Map.of("error", "OTP ou Token temporaire invalide"));
        }

        // supprimer OTP si celui-ci correct
        otpService.clearOtp(username);

        // recuperation de l'utilisateur pour jwt token
        Utilisateurs utilisateur = utilisateurRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));

        String jwtToken = jwtService.generateAccessToken(utilisateur);
        return ResponseEntity.ok(Map.of("accessToken", jwtToken));

    }

    /**
     * Si accessToken n'est plus valide, refreshToken en genre un autre
     * 
     * @param request
     * @return
     */
    @PostMapping("/refreshToken")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {

        String refreshToken = request.get("refreshToken");
        if (refreshToken == null) {
            return ResponseEntity.badRequest().body("Refresh token manquant");
        }

        String username = jwtService.getUsernameFromToken(refreshToken);

        // case refresh token not valid
        if (username == null) {
            return ResponseEntity.status(401).body("Refresh token invalide");
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
        if (!jwtService.isTokenValide(refreshToken, userDetails)) {
            return ResponseEntity.status(401).body(Map.of("error", "refresh token invalide"));
        }

        // case refreshToken is valide alors generer un nouveau acces token
        Utilisateurs utilisateur = utilisateurRepo.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouve"));
        String newAccessToken = jwtService.generateRefreshToken(utilisateur);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

    }

}
