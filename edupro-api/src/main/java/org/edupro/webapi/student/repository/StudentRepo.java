package org.edupro.webapi.student.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.student.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, String> {
    Optional<StudentEntity> findByNisn(String name);
    List<StudentEntity> findAllByStatus(DataStatus status);
    boolean existsByNisn(String nisn);
    int countByStatus(DataStatus status);
}
