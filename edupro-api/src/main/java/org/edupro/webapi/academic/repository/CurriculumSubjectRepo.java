package org.edupro.webapi.academic.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.academic.model.CurriculumSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumSubjectRepo extends JpaRepository<CurriculumSubjectEntity, String> {
    List<CurriculumSubjectEntity> findAllByStatus(DataStatus status);
    boolean existsByCode(String code);
}