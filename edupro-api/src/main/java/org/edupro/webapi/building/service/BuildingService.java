package org.edupro.webapi.building.service;

import org.edupro.webapi.building.model.BuildingReq;
import org.edupro.webapi.building.model.BuildingRes;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    List<BuildingRes> get();
    Optional<BuildingRes> getById(String kode);
    Optional<BuildingRes> save(BuildingReq request);
    Optional<BuildingRes> update(BuildingReq request, String kode);
    Optional<BuildingRes> delete(String kode);
}
