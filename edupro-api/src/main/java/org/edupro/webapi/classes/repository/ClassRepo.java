package org.edupro.webapi.classes.repository;

import org.edupro.webapi.classes.model.ClassEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<ClassEntity, String> {
    List<ClassEntity> findAllByStatus(DataStatus status);
    boolean existsByCode(String code);
}