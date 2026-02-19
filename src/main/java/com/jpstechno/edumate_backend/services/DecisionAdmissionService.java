package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.AdmissionDecisions;

public interface DecisionAdmissionService {

    List<AdmissionDecisions> getAllDecisionParDemande(String numeroDemande);

    AdmissionDecisions ajouterUneDecision(AdmissionDecisions decisions);

}
