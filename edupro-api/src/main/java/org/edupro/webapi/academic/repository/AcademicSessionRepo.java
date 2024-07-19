package org.edupro.webapi.academic.repository;

import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicSessionRepo extends JpaRepository<AcademicSessionEntity, String> {
    List<AcademicSessionEntity> findAllByStatus(DataStatus status);
    boolean existsByAcademicYearIdAndSemester(String academicYearId, Integer semester);
}