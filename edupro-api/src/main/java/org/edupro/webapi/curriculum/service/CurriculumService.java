package org.edupro.webapi.curriculum.service;

import org.edupro.webapi.curriculum.model.CurriculumReq;
import org.edupro.webapi.curriculum.model.CurriculumRes;

import java.util.List;
import java.util.Optional;

public interface CurriculumService {
    List<CurriculumRes> get();
    Optional<CurriculumRes> getById(String kode);
    Optional<CurriculumRes> save(CurriculumReq request);
    Optional<CurriculumRes> update(CurriculumReq request, String kode);
    Optional<CurriculumRes> delete(String kode);
}
