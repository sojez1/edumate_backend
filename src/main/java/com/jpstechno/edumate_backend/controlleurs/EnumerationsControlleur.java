package com.jpstechno.edumate_backend.controlleurs;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.edumate_backend.modeles.enumerations.OrdreEnseignements;
import com.jpstechno.edumate_backend.modeles.enumerations.TypeParents;
import com.jpstechno.edumate_backend.utilitaires.EnumsAsList;

@RestController
@RequestMapping("/enumerations")
@CrossOrigin
public class EnumerationsControlleur {

    @GetMapping("/ordreEnseignements")
    public List<String> getOrdreEnseignements() {
        return EnumsAsList.getEnumList(OrdreEnseignements.class);
    }

    @GetMapping("/getTypeParents")
    public List<String> getTypeParents() {
        return EnumsAsList.getEnumList(TypeParents.class);
    }

}
