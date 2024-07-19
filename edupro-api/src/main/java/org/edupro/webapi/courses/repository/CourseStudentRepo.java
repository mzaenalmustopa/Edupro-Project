package org.edupro.webapi.courses.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.courses.model.CourseStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseStudentRepo extends JpaRepository<CourseStudentEntity, String> {
    List<CourseStudentEntity> findAllByCourseIdAndStatus(String courseId, DataStatus status);
    Integer countAllByStatus(DataStatus status);
}
