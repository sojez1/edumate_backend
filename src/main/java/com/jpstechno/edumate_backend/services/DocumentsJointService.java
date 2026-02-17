package com.jpstechno.edumate_backend.services;

import java.util.List;

import com.jpstechno.edumate_backend.modeles.DocumentsJoints;
import com.jpstechno.edumate_backend.modeles.dto.GetAdmissionDetailsForm;

public interface DocumentsJointService {

    DocumentsJoints ajouterUnDocument(DocumentsJoints docs);

    List<DocumentsJoints> ajouterDesDocuments(List<DocumentsJoints> mesDocs);

    List<DocumentsJoints> getAllDocumentsJointPourAdmission(String numeroDemandeAdmission);

}
