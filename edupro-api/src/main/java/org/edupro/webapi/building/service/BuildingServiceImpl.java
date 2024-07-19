package org.edupro.webapi.building.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.building.model.BuildingEntity;
import org.edupro.webapi.building.model.BuildingReq;
import org.edupro.webapi.building.model.BuildingRes;
import org.edupro.webapi.building.repository.BuildingRepo;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.util.CommonUtil;
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
public class BuildingServiceImpl extends BaseService implements BuildingService {
    private final BuildingRepo repo;

    @Override
    public List<BuildingRes> get() {
        var userId = this.getUserInfo();
        List<BuildingEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<BuildingRes> getById(String id) {
        BuildingEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<BuildingRes> save(BuildingReq request) {
        if(repo.existsByCode(request.getCode())){
            log.info("Save Gedung gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        BuildingEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<BuildingRes> update(BuildingReq request, String id) {
        BuildingEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<BuildingRes> delete(String id) {
        BuildingEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);
        return saveOrUpdate(result);
    }

    private Optional<BuildingRes> saveOrUpdate(BuildingEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Gedung, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Gedung gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private BuildingEntity getEntityById(String id) {
        BuildingEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private BuildingRes convertEntityToRes(BuildingEntity entity){
        BuildingRes result = new BuildingRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private BuildingEntity convertReqToEntity(BuildingReq request){
        BuildingEntity result = new BuildingEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
        return result;
    }

    private void convertReqToEntity(BuildingReq request, BuildingEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());
    }
}
