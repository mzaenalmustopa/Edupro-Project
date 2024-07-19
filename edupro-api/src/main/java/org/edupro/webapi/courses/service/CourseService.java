package org.edupro.webapi.courses.service;

import org.edupro.webapi.courses.model.CoursePeopleRes;
import org.edupro.webapi.courses.model.CoursePersonReq;
import org.edupro.webapi.courses.model.CoursePersonRes;
import org.edupro.webapi.courses.model.CourseReq;
import org.edupro.webapi.courses.model.CourseRes;
import org.edupro.webapi.courses.model.CourseStudentReq;
import org.edupro.webapi.courses.model.CourseStudentRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get();
    List<CourseRes> getByUserId(String userId);
    Optional<CoursePeopleRes> getPeopleById(String id);
    Optional<CourseStudentRes> saveSiswa(String id, CourseStudentReq request);
    List<CourseStudentRes> saveSiswaList(List<CourseStudentReq> request);
    Optional<CoursePersonRes> savePerson(String id, CoursePersonReq request);
    List<CoursePersonRes> savePersonList(List<CoursePersonReq> request);
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
