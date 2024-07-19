package org.edupro.webapi.curriculum.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.curriculum.model.CurriculumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurriculumRepo extends JpaRepository<CurriculumEntity, String> {
    List<CurriculumEntity> findAllByStatus(DataStatus status);
    Optional<CurriculumEntity> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByName(String name);
    int countAllByStatus(DataStatus status);
}
