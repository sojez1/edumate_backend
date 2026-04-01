package com.jpstechno.edumate_backend.securityconfig;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.utilitaires.MailCoursier;

@Service
public class OtpService {

    private MailCoursier mailService;

    private Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private Map<String, Long> otpExpirationDate = new ConcurrentHashMap<>();
    final long otpDureeExpiration = 5 * 60 * 1000; // 5 mn converti en millisecond

    public OtpService(MailCoursier mailService) {
        this.mailService = mailService;

    }

    /**
     * Genere un code OTP pour 2FA
     * 
     * @param username Username de l'utilisateur (habituellement son courriel)
     * @return une chaine de caractere comportant le code OTP
     */
    public String generateOTP(String username) {

        int otpCode = new Random().nextInt(900000) + 100000;
        String otp = String.valueOf(otpCode);

        otpStorage.put(username, otp);
        otpExpirationDate.put(username, System.currentTimeMillis() + otpDureeExpiration);

        return otp;

    }

    /**
     * Verifie si le code OTP est valide
     * non expiree
     * correspond vraiment a l'utilisateuir (courriel)
     * 
     * @param username username de celui qui tente de se connecter (habituellement
     *                 son email)
     * @param otp      code OTP a verifier
     * @return true si OTP valide, sinon false
     */
    public boolean isOtpValide(String username, String otp) {
        String otpSaved = otpStorage.get(username);
        Long expirationDate = otpExpirationDate.get(username);

        if (otpSaved == null || expirationDate == null || System.currentTimeMillis() > expirationDate) {
            return false;
        }

        return otpSaved.equals(otp);
    }

    /**
     * Sert a supprimer un code OTP deja utilise.
     * 
     * @param username email de l'utilisateur. Peut etre obtenu avec le subject du
     *                 claims
     */
    public void clearOtp(String username) {
        otpStorage.remove(username);
        otpExpirationDate.remove(username);
    }

    /**
     * Envoi un courriel comportant le code OTP
     * 
     * @param username courriel de l'utilisateur qui tente de se connecter
     * @param otp      code OTP
     */
    public void sendOtp(String username, String otp) {

        String texte = "Code pour authentification dans Edumate est : " + otp + " et s'expire dans 5 mn";
        String mailObjet = "Edumate 2FA authentication";
        mailService.SendMail(username, texte, mailObjet);

    }

}
