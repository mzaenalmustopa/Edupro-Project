package org.edupro.webapi.subject.service;

import org.edupro.webapi.subject.repository.SubjectReq;
import org.edupro.webapi.subject.repository.SubjectRes;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<SubjectRes> get();
    Optional<SubjectRes> getById(String kode);
    Optional<SubjectRes> save(SubjectReq request);
    Optional<SubjectRes> update(SubjectReq request, String kode);
    Optional<SubjectRes> delete(String kode);
}
