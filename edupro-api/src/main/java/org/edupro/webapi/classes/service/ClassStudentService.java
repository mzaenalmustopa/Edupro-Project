package org.edupro.webapi.classes.service;

import org.edupro.webapi.classes.model.ClassStudentReq;
import org.edupro.webapi.classes.model.ClassStudentRes;

import java.util.List;
import java.util.Optional;

public interface ClassStudentService {
    List<ClassStudentRes> get();
    Optional<ClassStudentRes> getById(String kode);
    Optional<ClassStudentRes> save(ClassStudentReq request);
    Optional<ClassStudentRes> save(String kelasId, List<ClassStudentReq> request);
    Optional<ClassStudentRes> update(ClassStudentReq request, String kode);
    Optional<ClassStudentRes> delete(String kode);
}
