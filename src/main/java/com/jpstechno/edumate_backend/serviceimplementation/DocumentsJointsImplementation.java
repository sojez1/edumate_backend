package com.jpstechno.edumate_backend.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpstechno.edumate_backend.modeles.DocumentsJoints;

import com.jpstechno.edumate_backend.repositories.DocumentsJointsRepo;
import com.jpstechno.edumate_backend.services.DocumentsJointService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentsJointsImplementation implements DocumentsJointService {

    private final DocumentsJointsRepo docsrepo;

    @Override
    public DocumentsJoints ajouterUnDocument(DocumentsJoints docs) {
        return docsrepo.save(docs);
    }

    @Override
    public List<DocumentsJoints> ajouterDesDocuments(List<DocumentsJoints> mesDocs) {
        return docsrepo.saveAll(mesDocs);
    }

    @Override
    public List<DocumentsJoints> getAllDocumentsJointPourAdmission(String numeroDemandeAdmission) {
        return docsrepo.findDocumentsByNumeroAdmission(numeroDemandeAdmission);

    }

}
