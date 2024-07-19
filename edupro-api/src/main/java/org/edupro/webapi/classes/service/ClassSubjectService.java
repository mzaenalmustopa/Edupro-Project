package org.edupro.webapi.classes.service;

import org.edupro.webapi.classes.model.ClassSubjectReq;
import org.edupro.webapi.classes.model.ClassSubjectRes;

import java.util.List;
import java.util.Optional;

public interface ClassSubjectService {
    List<ClassSubjectRes> get();
    Optional<ClassSubjectRes> getById(String id);
    Optional<ClassSubjectRes> save(ClassSubjectReq request);
    Optional<ClassSubjectRes> update(ClassSubjectReq request, String id);
    Optional<ClassSubjectRes> delete(String id);
}
