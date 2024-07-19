package org.edupro.webapi.student.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.student.model.StudentScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentScoreRepo extends JpaRepository<StudentScoreEntity, String> {
    List<StudentScoreEntity> findAllByStatus(DataStatus status);
}
