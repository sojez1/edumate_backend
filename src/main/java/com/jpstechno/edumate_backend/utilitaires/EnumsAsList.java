package com.jpstechno.edumate_backend.utilitaires;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Cet utilitaire permet de convertir les enumerations en liste de String.
 * Utiles pour recuperer les valeurs des enumerations afin de les utiliser dans
 * un combo box ou autre.
 * Cette liste peut etre retournee via une API REST
 * pour etre utilisee cote front-end dans les formulaires
 */
@Service
public class EnumsAsList<T extends Enum<T>> {

    public static <E extends Enum<E>> List<String> getEnumList(Class<E> enumClass) {
        List<String> enumList = new ArrayList<>();
        for (E enumConstant : enumClass.getEnumConstants()) {
            enumList.add(enumConstant.name());
        }
        return enumList;
    }
}
