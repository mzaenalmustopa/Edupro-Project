package org.edupro.webapi.classes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.classes.model.ClassSubjectEntity;
import org.edupro.webapi.classes.repository.ClassSubjectRepo;
import org.edupro.webapi.classes.model.ClassSubjectReq;
import org.edupro.webapi.classes.model.ClassSubjectRes;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.subject.repository.SubjectRepo;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassSubjectServiceImpl implements ClassSubjectService {
    private final ClassSubjectRepo repo;
    private final SubjectRepo subjectRepo;

    @Override
    public List<ClassSubjectRes> get() {
        List<ClassSubjectEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<ClassSubjectRes> getById(String id) {
        ClassSubjectEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<ClassSubjectRes> save(ClassSubjectReq request) {
        if(repo.existsByClassIdAndSubjectId(request.getKelasId(), request.getMapelId())){
            Map<String, String> errors = Map.of("kode", "kelas "+ request.getKelasId()+" dan mapel "+ request.getMapelId() +" sudah digunakan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        ClassSubjectEntity result = this.convertReqToEntity(request);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassSubjectRes> update(ClassSubjectReq request, String id) {
        ClassSubjectEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassSubjectRes> delete(String id) {
        ClassSubjectEntity result = this.getEntityById(id);
        result.setStatus(DataStatus.DELETED);

        return saveOrUpdate(result);
    }

    private Optional<ClassSubjectRes> saveOrUpdate(ClassSubjectEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelompok Mapel, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelompok Mapel gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private ClassSubjectEntity getEntityById(String id) {
        ClassSubjectEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("id", "Id "+ id +" tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private ClassSubjectRes convertEntityToRes(ClassSubjectEntity entity){
        ClassSubjectRes result = new ClassSubjectRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private ClassSubjectEntity convertReqToEntity(ClassSubjectReq request){
        ClassSubjectEntity result = new ClassSubjectEntity();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private void convertReqToEntity(ClassSubjectReq request, ClassSubjectEntity result){
        BeanUtils.copyProperties(request, result);
    }
}
