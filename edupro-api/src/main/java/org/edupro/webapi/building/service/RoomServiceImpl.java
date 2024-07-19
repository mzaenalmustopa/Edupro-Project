package org.edupro.webapi.building.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.building.model.BuildingEntity;
import org.edupro.webapi.building.repository.BuildingRepo;
import org.edupro.webapi.building.repository.RoomRepo;
import org.edupro.webapi.building.model.RoomEntity;
import org.edupro.webapi.building.model.RoomRes;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.building.model.RoomReq;
import org.edupro.webapi.base.service.BaseService;
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
public class RoomServiceImpl extends BaseService implements RoomService {
    private final RoomRepo repo;
    private final BuildingRepo buildingRepo;

    @Override
    public List<RoomRes> get() {
        List<RoomEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<RoomRes> getById(String id) {
        RoomEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<RoomRes> save(RoomReq request) {
        if(repo.existsByCode(request.getCode())){
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        RoomEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<RoomRes> update(RoomReq request, String id) {
        RoomEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);

        return saveOrUpdate(result);
    }

    @Override
    public Optional<RoomRes> delete(String id) {
        RoomEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<RoomRes> saveOrUpdate(RoomEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Ruangan, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Ruangan gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private BuildingEntity getGedungById(String id) {
        BuildingEntity result = this.buildingRepo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private RoomEntity getEntityById(String id) {
        RoomEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private RoomRes convertEntityToRes(RoomEntity entity){
        RoomRes result = new RoomRes();
        BeanUtils.copyProperties(entity, result);
        if(entity.getBuilding() != null){
            if(entity.getBuilding().getCode() != null) result.setBuildingCode(entity.getBuilding().getCode());

            if (entity.getBuilding().getName() != null) result.setBuildingName(entity.getBuilding().getName());
        }
        return result;
    }

    private RoomEntity convertReqToEntity(RoomReq request){
        BuildingEntity gedung =  this.getGedungById(request.getBuildingId());
        if(gedung.getId().isEmpty()){
            Map<String, String> errors = Map.of("gedungId", "gedungId "+ request.getBuildingId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        RoomEntity result = new RoomEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(RoomReq request, RoomEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        BuildingEntity buildingEntity =  this.getGedungById(request.getBuildingId());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
