package org.edupro.webapi.courses.service;

import org.edupro.webapi.courses.model.CourseSectionReq;
import org.edupro.webapi.courses.model.CourseSectionRes;

import java.util.List;
import java.util.Optional;

public interface CourseSectionService {
    List<CourseSectionRes> getByCourseId(String courseId);
    List<CourseSectionRes> getByTopic(String courseId);
    Optional<CourseSectionRes> getById(String id);
    Optional<CourseSectionRes> save(String id, CourseSectionReq request);
    Optional<CourseSectionRes> update(CourseSectionReq request, String id);
    Optional<CourseSectionRes> delete(String id);
}
