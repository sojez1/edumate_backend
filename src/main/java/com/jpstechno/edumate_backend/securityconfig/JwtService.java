package com.jpstechno.edumate_backend.securityconfig;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.Utilisateurs;
import com.jpstechno.edumate_backend.modeles.enumerations.RoleUtilisateurs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    int dureeToken = 5 * 60 * 1000; // 5 mn convertie en millisecond
    int dureeRefreshToken = 3 * 24 * 60 * 60 * 1000; // 3 jrs en millisecond
    private final String secret = "abcdgcgncbccvxvxxMvbvbbBbbnbxvcvbcvcghfhgbgdg";
    // private Utilisateurs utilisateur;

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public String generateAccessToken(Utilisateurs utilisateur) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", utilisateur.getId());
        claims.put("nom", utilisateur.getPrenoms() + " " + utilisateur.getNom().substring(0, 1) + ".");
        claims.put("roles", utilisateur.getRole());

        return Jwts.builder()
                .claims(claims)
                .subject(utilisateur.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + dureeToken))
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(Utilisateurs utilisateur) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", utilisateur.getId());
        claims.put("nom", utilisateur.getPrenoms() + " " + utilisateur.getNom().substring(0, 1) + ".");
        claims.put("roles", utilisateur.getRole());

        return Jwts.builder()
                .claims(claims)
                .subject(utilisateur.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + dureeRefreshToken))
                .signWith(getKey())
                .compact();

    }

    public String getUsernameFromToken(String token) {
        return this.extractClaimsFromToken(token).getSubject();
    }

    public long getUserIdFromToken(String token) {
        String userId_string = extractClaimsFromToken(token).getId();
        return Long.parseLong(userId_string);
    }

    public List<RoleUtilisateurs> getUserRoleFromToken(String token) {
        return (List<RoleUtilisateurs>) this.extractClaimsFromToken(token).get("roles");
    }

    private boolean isTokenExpired(String token) {
        return extractClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isTokenValide(String token, UserDetails userDetails) {
        boolean tokenExpired = isTokenExpired(token);
        boolean isRealUser = userDetails.getUsername().equals(getUsernameFromToken(token));
        return !tokenExpired && isRealUser;
    }

}
