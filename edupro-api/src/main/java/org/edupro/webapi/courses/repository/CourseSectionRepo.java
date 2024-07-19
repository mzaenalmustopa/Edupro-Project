package org.edupro.webapi.courses.repository;

import org.apache.ibatis.annotations.Param;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.courses.model.CourseSectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSectionRepo extends JpaRepository<CourseSectionEntity, String> {
    List<CourseSectionEntity> findAllByCourseId(String courseId);
    List<CourseSectionEntity> findAllByStatus(DataStatus status);
    List<CourseSectionEntity> findAllByName(String name);

    @Query("select max(t.noUrut) from CourseSectionEntity t where t.courseId = :courseId and t.parentId is null")
    Integer findMaxNoUrutByCourseId(@Param("courseId") String courseId);

    @Query("select max(t.noUrut) from CourseSectionEntity t where t.parentId = :parentId")
    Integer findMaxNoUrutByParentId(@Param("parentId") String parentId);
}
