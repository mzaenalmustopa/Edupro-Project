package org.edupro.webapi.classes.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.classes.model.ClassAbsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassAbsentRepo extends JpaRepository<ClassAbsentEntity, String> {
    List<ClassAbsentEntity> findAllByStatus(DataStatus status);
}
