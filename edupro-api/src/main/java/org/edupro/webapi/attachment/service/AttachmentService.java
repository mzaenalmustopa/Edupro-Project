package org.edupro.webapi.attachment.service;

import org.edupro.webapi.attachment.model.AttachmentRes;
import org.edupro.webapi.attachment.model.AttachmentReq;

import java.util.List;
import java.util.Optional;

public interface AttachmentService {
    List<AttachmentRes> get();
    Optional<AttachmentRes> getById(String id);
    Optional<AttachmentRes> save(AttachmentReq request);
    Optional<AttachmentRes> update(AttachmentReq request, String id);
    Optional<AttachmentRes> delete(String id);
}
