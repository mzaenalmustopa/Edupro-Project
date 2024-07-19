package org.edupro.webapi.building.repository;

import org.edupro.webapi.building.model.BuildingEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepo extends JpaRepository<BuildingEntity, String> {
    List<BuildingEntity> findAllByStatus(DataStatus status);
    boolean existsByCode(String code);
    boolean existsById(String id);
}
