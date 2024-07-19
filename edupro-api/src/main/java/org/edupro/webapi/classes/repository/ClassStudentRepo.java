package org.edupro.webapi.classes.repository;

import org.edupro.webapi.classes.model.ClassStudentEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentRepo extends JpaRepository<ClassStudentEntity, String> {
    List<ClassStudentEntity> findAllByStatus(DataStatus status);
    List<ClassStudentEntity> findAllByClassId(String classId);
    boolean existsByClassIdAndStudentId(String classId, String studentId);
}