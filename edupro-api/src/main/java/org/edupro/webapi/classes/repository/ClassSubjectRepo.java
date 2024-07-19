package org.edupro.webapi.classes.repository;

import org.edupro.webapi.classes.model.ClassSubjectEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassSubjectRepo extends JpaRepository<ClassSubjectEntity, String> {
    List<ClassSubjectEntity> findAllByStatus(DataStatus status);
    boolean existsByClassIdAndSubjectId(String classId, String subjectId);
}