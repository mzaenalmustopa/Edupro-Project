package org.edupro.webapi.academic.repository;

import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicYearRepo extends JpaRepository<AcademicYearEntity, String> {
    List<AcademicYearEntity> findAllByStatus(DataStatus status);
    List<AcademicYearEntity> findAllByCurriculumId(String curriculumId);
    boolean existsByName(String name);
    int countByStatus(DataStatus status);
}