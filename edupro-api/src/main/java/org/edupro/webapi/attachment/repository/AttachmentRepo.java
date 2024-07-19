package org.edupro.webapi.attachment.repository;

import org.edupro.webapi.attachment.model.AttachmentEntity;
import org.edupro.webapi.constant.DataStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepo extends JpaRepository<AttachmentEntity, String> {
    List<AttachmentEntity> findAllByStatus(DataStatus status);
}
