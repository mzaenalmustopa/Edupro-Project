package org.edupro.webapi.academic.service;

import org.edupro.webapi.academic.model.AcademicSessionReq;
import org.edupro.webapi.academic.model.AcademicSessionRes;

import java.util.List;
import java.util.Optional;

public interface AcademicSessionService {
    List<AcademicSessionRes> get();
    Optional<AcademicSessionRes> getById(String id);
    Optional<AcademicSessionRes> save(AcademicSessionReq request);
    Optional<AcademicSessionRes> update(AcademicSessionReq request, String id);
    Optional<AcademicSessionRes> delete(String id);
}
