package org.edupro.webapi.subject.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.subject.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<SubjectEntity, String> {
    List<SubjectEntity> findAllByStatus(DataStatus status);
    boolean existsByCode(String code);
    boolean existsById(String id);
}
