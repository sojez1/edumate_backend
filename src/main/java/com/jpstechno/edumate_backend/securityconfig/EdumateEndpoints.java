package com.jpstechno.edumate_backend.securityconfig;

import java.util.List;

public class EdumateEndpoints {

    public static final String[] PUBLIC_ENDPOINTS = {
            "/auth/**",
            "/utilisateurs/new_user/**",
            "/classes/listerClasses/**",
            "/anneeScolaires/lister/**",
            "/demande-admission/soumettre",
            "/enumerations/**"
    };

    public static final String[] ADMIN_ENDPOINTS = {
            "/admin/**"
    };

    public static final String[] ENSEIGNANT_ENDPOINTS = {
            "/enseignant/**"
    };

    public static final List<String> ETUDIANT_ENDPOINTS = List.of(
            "",
            "",
            "");
}
