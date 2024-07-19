package org.edupro.webapi.building.repository;

import org.edupro.webapi.building.model.RoomEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepo extends JpaRepository<RoomEntity, String> {
    @Query("select t from RoomEntity t join fetch t.building")
    List<RoomEntity> findAllByStatus(DataStatus status);

    @Query("select t from RoomEntity t join fetch t.building where t.code = :kode")
    Optional<RoomEntity> findByCode(@Param("kode") String kode);
    boolean existsByCode(String kode);
}
