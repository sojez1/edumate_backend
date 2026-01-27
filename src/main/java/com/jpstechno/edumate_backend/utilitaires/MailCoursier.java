package com.jpstechno.edumate_backend.utilitaires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailCoursier {

    @Autowired
    private final JavaMailSender mailSender;

    public void SendMail(String destinataire, String texte, String objet) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinataire);
        message.setSubject(objet);
        message.setText(texte);
        mailSender.send(message);

    }
}
