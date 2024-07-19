package org.edupro.webapi.subject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.subject.model.SubjectEntity;
import org.edupro.webapi.subject.repository.SubjectRepo;
import org.edupro.webapi.subject.repository.SubjectReq;
import org.edupro.webapi.subject.repository.SubjectRes;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectServiceImpl extends BaseService implements SubjectService {
    private final SubjectRepo repo;

    @Override
    public List<SubjectRes> get() {
        List<SubjectEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<SubjectRes> getById(String id) {
        SubjectEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<SubjectRes> save(SubjectReq request) {
        if(repo.existsByCode(request.getCode())){
            log.info("Save Mapel gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        SubjectEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<SubjectRes> update(SubjectReq request, String id) {
        SubjectEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<SubjectRes> delete(String id) {
        SubjectEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<SubjectRes> saveOrUpdate(SubjectEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Mapel, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Mapel gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private SubjectEntity getEntityById(String id) {
        SubjectEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private SubjectRes convertEntityToRes(SubjectEntity entity){
        SubjectRes result = new SubjectRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private SubjectEntity convertReqToEntity(SubjectReq request){
        SubjectEntity result = new SubjectEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(SubjectReq request, SubjectEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
