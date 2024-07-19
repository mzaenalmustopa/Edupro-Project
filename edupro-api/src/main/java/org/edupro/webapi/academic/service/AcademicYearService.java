package org.edupro.webapi.academic.service;

import org.edupro.webapi.academic.model.AcademicYearReq;
import org.edupro.webapi.academic.model.AcademicYearRes;

import java.util.List;
import java.util.Optional;

public interface AcademicYearService {
    List<AcademicYearRes> get();
    List<AcademicYearRes> getByKurikulumId(String kurikulumId);
    Optional<AcademicYearRes> getById(String id);
    Optional<AcademicYearRes> save(AcademicYearReq request);
    Optional<AcademicYearRes> update(AcademicYearReq request, String id);
    Optional<AcademicYearRes> delete(String id);
}
