package com.jpstechno.edumate_backend.modeles.enumerations;

public enum OrdreEnseignements {
    MATERNELLE("Maternelle"),
    PRIMAIRE("Primaire"),
    SECONDAIRE("Secondaire"),
    SUPERIEUR("Supérieur");

    private String code;

    OrdreEnseignements(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
