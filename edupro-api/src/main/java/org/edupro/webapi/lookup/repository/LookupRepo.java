package org.edupro.webapi.lookup.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.lookup.model.LookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LookupRepo extends JpaRepository<LookupEntity, String> {
    List<LookupEntity> findAllByStatus(DataStatus status);
    boolean existsByGroupAndCode(String group, String code);
    int countAllByGroup(String group);
    List<LookupEntity> findAllByGroup(String group);
}