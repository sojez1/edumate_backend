package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpstechno.edumate_backend.AppEvents.NouveauUtilisateurEvent;
import com.jpstechno.edumate_backend.modeles.Etudiants;
import com.jpstechno.edumate_backend.modeles.HistoriqueClassesEtudiant;
import com.jpstechno.edumate_backend.modeles.OldStudentRegistrationData;
import com.jpstechno.edumate_backend.modeles.Parents;

import com.jpstechno.edumate_backend.repositories.EtudiantRepo;
import com.jpstechno.edumate_backend.repositories.HistoriqueClasseRepo;
import com.jpstechno.edumate_backend.repositories.UtilisateurRepo;
import com.jpstechno.edumate_backend.services.EtudiantService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtudiantImplementation implements EtudiantService {

    private final PasswordEncoder passwordEncoder;
    private final EtudiantRepo etudiantRepo;
    private final HistoriqueClasseRepo historiqueClasseRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final ApplicationEventPublisher eventPublisheur;

    @Override
    public List<Parents> getParents() {
        return null;
    }

    @Override
    @Transactional
    public String enrolerAncienEtudiant(OldStudentRegistrationData studentData) {

        String encodedPassword = passwordEncoder.encode(studentData.getEtudiant().getPassword());

        Etudiants etudiant = new Etudiants();
        etudiant.setActif(false);
        etudiant.setEmail(studentData.getEtudiant().getEmail());
        etudiant.setMatricule(studentData.getEtudiant().getMatricule());
        etudiant.setNom(studentData.getEtudiant().getNom());
        etudiant.setPassword(encodedPassword);
        etudiant.setPrenoms(studentData.getEtudiant().getPrenoms());
        etudiant.setTelephone(studentData.getEtudiant().getTelephone());
        etudiant.setUsername(studentData.getEtudiant().getUsername());
        etudiant.setValideEmail(false);
        Etudiants savedEtudiant = utilisateurRepo.save(etudiant);

        HistoriqueClassesEtudiant historique = new HistoriqueClassesEtudiant();
        historique.setAnneeScolaire(studentData.getAnneeScolaire());
        historique.setClasse(studentData.getClasse());
        historique.setEtudiant(savedEtudiant);
        historiqueClasseRepo.save(historique);

        // create email validation url
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/utilisateurs/verifierEmail")
                .queryParam("userId", etudiant.getId())
                .toUriString();

        // publier evenement de creation d'un nouveau etudiant
        NouveauUtilisateurEvent newStudent = new NouveauUtilisateurEvent(this, etudiant, url);
        eventPublisheur.publishEvent(newStudent);

        return "Enregistrement de l'ancien etudiant reussi";
    }

    @Override
    public ResponseEntity<List<HistoriqueClassesEtudiant>> getHistoriqueClasseEtudiant(Etudiants etudiant) {
        List<HistoriqueClassesEtudiant> reponse = historiqueClasseRepo.findByEtudiant(etudiant);
        // delete student password before return data to client
        reponse.forEach(student -> student.getEtudiant().setPassword(""));
        return new ResponseEntity<>(reponse, HttpStatus.OK);

    }

    @Override
    public List<HistoriqueClassesEtudiant> getConnectedStudentData() {
        // recuperation de l<utilisateur connecte
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            // get Utilisateur connecte
            String username = auth.getName();
            Etudiants etu = etudiantRepo.findByUsername(username).get();
            List<HistoriqueClassesEtudiant> reponse = historiqueClasseRepo.findByEtudiant(etu);

            // clean passord for safety before returning it to client
            reponse.forEach(histo -> histo.getEtudiant().setPassword(""));

            return reponse;

        } else {
            throw new RuntimeException("Vous devez vous connectes pour acceder a ce service");
        }
    }

}
