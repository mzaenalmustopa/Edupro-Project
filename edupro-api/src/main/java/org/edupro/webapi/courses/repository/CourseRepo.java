package org.edupro.webapi.courses.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.courses.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, String> {
    List<CourseEntity> findAllByStatus(DataStatus status);
    List<CourseEntity> findAllByStatusAndCreatedBy(DataStatus status, String createdBy);
    Integer countAllByStatusAndCreatedBy(DataStatus status, String createdBy);
    Integer countAllByStatus(DataStatus status);
}
