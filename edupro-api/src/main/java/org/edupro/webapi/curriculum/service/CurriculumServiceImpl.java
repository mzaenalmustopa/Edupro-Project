package org.edupro.webapi.curriculum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.curriculum.model.CurriculumEntity;
import org.edupro.webapi.curriculum.model.CurriculumReq;
import org.edupro.webapi.curriculum.model.CurriculumRes;
import org.edupro.webapi.curriculum.repository.CurriculumRepo;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.base.service.BaseService;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurriculumServiceImpl extends BaseService implements CurriculumService {
    private final CurriculumRepo repo;

    @Override
    public List<CurriculumRes> get() {
        List<CurriculumEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CurriculumRes> getById(String id) {
        CurriculumEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CurriculumRes> save(CurriculumReq request) {
        if(repo.existsByCode(request.getCode())){
            log.info("Save Curriculum gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        if(repo.existsByName(request.getName())){
            log.info("Save Curriculum gagal, terjadi error : name sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        CurriculumEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CurriculumRes> update(CurriculumReq request, String id) {
        CurriculumEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CurriculumRes> delete(String id) {
        CurriculumEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<CurriculumRes> saveOrUpdate(CurriculumEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Lembaga, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Lembaga gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private CurriculumEntity getEntityById(String id) {
        CurriculumEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CurriculumRes convertEntityToRes(CurriculumEntity entity){
        CurriculumRes result = new CurriculumRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CurriculumEntity convertReqToEntity(CurriculumReq request){
        CurriculumEntity result = new CurriculumEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(CurriculumReq request, CurriculumEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
