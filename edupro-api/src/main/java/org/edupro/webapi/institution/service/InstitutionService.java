package org.edupro.webapi.institution.service;

import org.edupro.webapi.institution.model.InstitutionReq;
import org.edupro.webapi.institution.model.InstitutionRes;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {
    List<InstitutionRes> get();
    Optional<InstitutionRes> getById(String id);
    Optional<InstitutionRes> save(InstitutionReq request);
    Optional<InstitutionRes> update(InstitutionReq request, String id);
    Optional<InstitutionRes> delete(String id);
}
