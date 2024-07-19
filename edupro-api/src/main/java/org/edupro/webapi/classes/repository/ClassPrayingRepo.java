package org.edupro.webapi.classes.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.student.model.StudentPrayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassPrayingRepo extends JpaRepository<StudentPrayEntity, String> {
    List<StudentPrayEntity> findAllByStatus(DataStatus status);
}
