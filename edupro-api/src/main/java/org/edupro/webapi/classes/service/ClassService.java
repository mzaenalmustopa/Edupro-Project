package org.edupro.webapi.classes.service;

import org.edupro.webapi.classes.model.ClassReq;
import org.edupro.webapi.classes.model.ClassRes;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<ClassRes> get();
    Optional<ClassRes> getById(String id);
    Optional<ClassRes> save(ClassReq request);
    Optional<ClassRes> update(ClassReq request, String id);
    Optional<ClassRes> delete(String id);
}
