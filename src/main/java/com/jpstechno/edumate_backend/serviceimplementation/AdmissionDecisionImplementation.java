package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.AdmissionDecisions;
import com.jpstechno.edumate_backend.repositories.AdmissionDecissionRepo;
import com.jpstechno.edumate_backend.services.DecisionAdmissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmissionDecisionImplementation implements DecisionAdmissionService {

    private final AdmissionDecissionRepo admissionDecisionRepo;

    @Override
    public List<AdmissionDecisions> getAllDecisionParDemande(String numeroDemande) {
        return admissionDecisionRepo.findByNumeroDemande(numeroDemande);
    }

    @Override
    public AdmissionDecisions ajouterUneDecision(AdmissionDecisions decisions) {
        return admissionDecisionRepo.save(decisions);
    }

}
