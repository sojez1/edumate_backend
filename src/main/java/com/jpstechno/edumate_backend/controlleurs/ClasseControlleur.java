package com.jpstechno.edumate_backend.controlleurs;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.Classes;
import com.jpstechno.edumate_backend.serviceimplementation.ClasseImplementation;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/classes")
@CrossOrigin
public class ClasseControlleur {

    private final ClasseImplementation classeImplementation;

    @PostMapping("/ajouterClasse")
    public ResponseEntity<Classes> createClasse(@RequestBody Classes classe) {
        Classes createdClasse = classeImplementation.createClasse(classe);
        return new ResponseEntity<>(createdClasse, HttpStatus.CREATED);
    }

    @GetMapping("/listerClasses")
    public ResponseEntity<Iterable<Classes>> listerClasses() {
        Iterable<Classes> classes = classeImplementation.getAllClasses();
        return ResponseEntity.ok(classes);
    }

}
