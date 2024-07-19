package org.edupro.webapi.institution.repository;

import org.edupro.webapi.institution.model.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepo extends JpaRepository<InstitutionEntity, String> {
    boolean existsByName(String name);
    boolean existsByShortName(String shortName);
    Optional<InstitutionEntity> findByName(String name);
}
