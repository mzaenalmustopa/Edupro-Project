package org.edupro.webapi.academic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.academic.model.AcademicYearReq;
import org.edupro.webapi.academic.model.AcademicYearRes;
import org.edupro.webapi.academic.repository.AcademicYearRepo;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.Formatter;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.curriculum.model.CurriculumEntity;
import org.edupro.webapi.curriculum.repository.CurriculumRepo;
import org.edupro.webapi.exception.EduProApiException;
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
public class AcademicYearServiceImpl extends BaseService implements AcademicYearService {
    private final CurriculumRepo curriculumRepo;
    private final AcademicYearRepo repo;

    @Override
    public List<AcademicYearRes> get() {
        List<AcademicYearEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public List<AcademicYearRes> getByKurikulumId(String kurikulumId) {
        List<AcademicYearEntity> result = this.repo.findAllByCurriculumId(kurikulumId);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<AcademicYearRes> getById(String id) {
        AcademicYearEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<AcademicYearRes> save(AcademicYearReq request) {
        if(repo.existsByName(request.getName())){
            Map<String, String> errors = Map.of("nama", "Nama "+ request.getName()+" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicYearEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<AcademicYearRes> update(AcademicYearReq request, String id) {
        AcademicYearEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<AcademicYearRes> delete(String id) {
        AcademicYearEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    public Optional<AcademicYearRes> saveOrUpdate(AcademicYearEntity entity) {
        try{
            this.repo.saveAndFlush(entity);
            return Optional.of(this.convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save TahunAjaran gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save TahunAjaran gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private AcademicYearEntity getEntityById(String id) {
        AcademicYearEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private AcademicYearRes convertEntityToRes(AcademicYearEntity entity){
        AcademicYearRes result = new AcademicYearRes();
        BeanUtils.copyProperties(entity, result);

        if(entity.getCurriculum() != null){
            result.setCurriculumCode(entity.getCurriculum().getCode());
            result.setCurriculumName(entity.getCurriculum().getName());
        }

        if (entity.getStartDate() != null){
            result.setStartDate(CommonUtil.toString(entity.getStartDate(), Formatter.DATE_FORMAT));
        }

        if (entity.getEndDate() != null){
            result.setEndDate(CommonUtil.toString(entity.getEndDate(), Formatter.DATE_FORMAT));
        }

        return result;
    }

    private AcademicYearEntity convertReqToEntity(AcademicYearReq request){
        CurriculumEntity curriculum = curriculumRepo.findById(request.getCurriculumId()).orElse(null);
        if(curriculum == null){
            Map<String, String> errors = Map.of("id", "curriculumId "+ request.getCurriculumId() +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicYearEntity result = new AcademicYearEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.ACTIVE);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(AcademicYearReq request, AcademicYearEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
