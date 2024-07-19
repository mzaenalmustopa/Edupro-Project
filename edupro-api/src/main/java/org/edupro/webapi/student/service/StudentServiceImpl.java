package org.edupro.webapi.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.Formatter;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.student.model.StudentEntity;
import org.edupro.webapi.student.model.StudentReq;
import org.edupro.webapi.student.model.StudentRes;
import org.edupro.webapi.student.repository.StudentRepo;
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
public class StudentServiceImpl extends BaseService implements StudentService {
    private final StudentRepo repo;

    @Override
    public List<StudentRes> get() {
        List<StudentEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        StudentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) {
        if(repo.existsByNisn(request.getNisn())){
            Map<String, String> errors = Map.of("nisn", "NISN "+ request.getNisn()+" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        StudentEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        StudentEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        StudentEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    public Optional<StudentRes> saveOrUpdate(StudentEntity entity) {
        try{
            this.repo.saveAndFlush(entity);
            return Optional.of(this.convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save Siswa gagal, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Siswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private StudentEntity getEntityById(String id) {
        StudentEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private StudentRes convertEntityToRes(StudentEntity entity){
        StudentRes result = new StudentRes();
        BeanUtils.copyProperties(entity, result);

        if(entity.getDob() != null){
            result.setDob(CommonUtil.toString(entity.getDob(), Formatter.DATE_FORMAT));
        }
        return result;
    }

    private StudentEntity convertReqToEntity(StudentReq request){
        StudentEntity result = new StudentEntity();
        BeanUtils.copyProperties(request, result);
        result.setStatus(DataStatus.ACTIVE);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(StudentReq request, StudentEntity result){
        result.setName(request.getName());
        result.setNisn(request.getNisn());
        result.setDob(request.getDob());
        result.setPob(request.getPob()
        );

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
