package org.edupro.webapi.building.service;

import org.edupro.webapi.building.model.RoomRes;
import org.edupro.webapi.building.model.RoomReq;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomRes> get();
    Optional<RoomRes> getById(String Id);
    Optional<RoomRes> save(RoomReq request);
    Optional<RoomRes> update(RoomReq request, String id);
    Optional<RoomRes> delete(String id);
}
