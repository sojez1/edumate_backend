package com.jpstechno.edumate_backend.securityconfig;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenTemporaireService {

    private Map<String, String> tempOtpStore = new ConcurrentHashMap<>();
    private Map<String, Long> tempOtpExpiration = new ConcurrentHashMap<>();
    private final long dureeToken = 5 * 60 * 1000; // 5 mn en milliseconds

    /**
     * Sert a creer un token temporaire
     * Ce token est envoye lorsque le username et password sont correct lors du
     * login
     * 
     * @param username
     * @return
     */
    public String create(String username) {
        String tempOtp = UUID.randomUUID().toString();
        tempOtpStore.put(username, tempOtp);
        tempOtpExpiration.put(username, System.currentTimeMillis() + dureeToken);
        return tempOtp;
    }

    public String validate(String otpTemp) {
        String username = tempOtpStore.get(otpTemp);
        Long expirationDate = tempOtpExpiration.get(username);

        if (username == null || expirationDate == null || System.currentTimeMillis() > expirationDate)
            return null;
        // supprimer le token temporaire apres validation de celui-ci
        tempOtpStore.remove(otpTemp);
        tempOtpExpiration.remove(otpTemp);

        return username;
    }

}
