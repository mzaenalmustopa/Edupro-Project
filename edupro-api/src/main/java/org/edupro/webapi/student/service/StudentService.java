package org.edupro.webapi.student.service;

import org.edupro.webapi.student.model.StudentReq;
import org.edupro.webapi.student.model.StudentRes;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentRes> get();
    Optional<StudentRes> getById(String id);
    Optional<StudentRes> save(StudentReq request);
    Optional<StudentRes> update(StudentReq request, String id);
    Optional<StudentRes> delete(String id);
}
