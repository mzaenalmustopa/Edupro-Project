package org.edupro.webapi.institution.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.Formatter;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.institution.model.InstitutionEntity;
import org.edupro.webapi.institution.model.InstitutionReq;
import org.edupro.webapi.institution.model.InstitutionRes;
import org.edupro.webapi.institution.repository.InstitutionRepo;
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
public class InstitutionServiceImpl extends BaseService implements InstitutionService {
    private final InstitutionRepo repo;

    @Override
    public List<InstitutionRes> get() {
        var userId = this.getUserInfo();
        List<InstitutionEntity> result = this.repo.findAll();
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<InstitutionRes> getById(String id) {
        InstitutionEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<InstitutionRes> save(InstitutionReq request) {
        InstitutionEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());

        return saveOrUpdate(result);
    }

    @Override
    public Optional<InstitutionRes> update(InstitutionReq request, String id) {
        InstitutionEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<InstitutionRes> delete(String id) {
        InstitutionEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            return Optional.empty();
        }

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<InstitutionRes> saveOrUpdate(InstitutionEntity result) {
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

    private InstitutionRes convertEntityToRes(InstitutionEntity entity){
        InstitutionRes result = new InstitutionRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getExpiredDate() != null){
            result.setExpiredDate(CommonUtil.toString(entity.getExpiredDate(), Formatter.DATE_FORMAT));
        }

        return result;
    }

    private InstitutionEntity convertReqToEntity(InstitutionReq request){
        InstitutionEntity result = new InstitutionEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(InstitutionReq request, InstitutionEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
