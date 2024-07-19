package org.edupro.webapi.student.service;

import org.edupro.webapi.student.model.StudentScoreReq;
import org.edupro.webapi.student.model.StudentScoreRes;

import java.util.List;
import java.util.Optional;

public interface StudentScoreService {
    List<StudentScoreRes> get();
    Optional<StudentScoreRes> getById(String id);
    Optional<StudentScoreRes> save(StudentScoreReq request);
    Optional<StudentScoreRes> update(StudentScoreReq request, String id);
    Optional<StudentScoreRes> delete(String id);
}
