package com.jpstechno.edumate_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpstechno.edumate_backend.modeles.DemandeAdmissions;
import com.jpstechno.edumate_backend.modeles.ClesComposes.DemandeAdmissionKey;
import java.util.Optional;

public interface DemandeAdmissionRepo extends JpaRepository<DemandeAdmissions, DemandeAdmissionKey> {

    Optional<DemandeAdmissions> findByNumeroDemande(String numeroDemande);

}
