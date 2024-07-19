package org.edupro.webapi.academic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.academic.model.AcademicSessionReq;
import org.edupro.webapi.academic.model.AcademicSessionRes;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.academic.repository.AcademicSessionRepo;
import org.edupro.webapi.academic.repository.AcademicYearRepo;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.Formatter;
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
public class AcademicSessionServiceImpl extends BaseService implements AcademicSessionService {
    private final AcademicSessionRepo repo;
    private final AcademicYearRepo academicYearRepo;

    @Override
    public List<AcademicSessionRes> get() {
        List<AcademicSessionEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<AcademicSessionRes> getById(String id) {
        AcademicSessionEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<AcademicSessionRes> save(AcademicSessionReq request) {
        if(repo.existsByAcademicYearIdAndSemester(request.getAcademicYearId(), request.getSemester())){
            Map<String, String> errors = Map.of("tahunAjaranId",  "academicYearId "+ request.getAcademicYearId() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicSessionEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<AcademicSessionRes> update(AcademicSessionReq request, String id) {
        AcademicSessionEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    public Optional<AcademicSessionRes> saveOrUpdate(AcademicSessionEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Sesi gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save SesiAkademik gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public Optional<AcademicSessionRes> delete(String id) {
        AcademicSessionEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DELETED);
        return saveOrUpdate(result);
    }

    private AcademicSessionEntity getEntityById(String id) {
        AcademicSessionEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private AcademicSessionRes convertEntityToRes(AcademicSessionEntity entity){
        AcademicSessionRes result = new AcademicSessionRes();
        BeanUtils.copyProperties(entity, result);

        if(entity.getAcademicYear() != null){
            result.setAcademicYearId(entity.getAcademicYear().getId());
            result.setAcademicYearName(entity.getAcademicYear().getName());
        }
        if (entity.getStartDate() != null){
            result.setStartDate(CommonUtil.toString(entity.getStartDate(), Formatter.DATE_FORMAT));
        }
        if (entity.getEndDate() != null){
            result.setEndDate(CommonUtil.toString(entity.getEndDate(), Formatter.DATE_FORMAT));
        }

        return result;
    }

    private AcademicSessionEntity convertReqToEntity(AcademicSessionReq request){
        AcademicYearEntity tahunAjaran = academicYearRepo.findById(request.getAcademicYearId()).orElse(null);
        if(tahunAjaran == null) {
            Map<String, String> errors = Map.of("tahunAjaranId","tahun ajaran tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicSessionEntity result = new AcademicSessionEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.ACTIVE);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(AcademicSessionReq request, AcademicSessionEntity result){
       BeanUtils.copyProperties(request, result);
       result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
