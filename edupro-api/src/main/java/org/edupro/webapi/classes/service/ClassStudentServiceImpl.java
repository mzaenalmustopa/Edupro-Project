package org.edupro.webapi.classes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.classes.model.ClassStudentEntity;
import org.edupro.webapi.classes.model.ClassStudentReq;
import org.edupro.webapi.classes.model.ClassStudentRes;
import org.edupro.webapi.classes.repository.ClassRepo;
import org.edupro.webapi.classes.repository.ClassStudentRepo;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.student.repository.StudentRepo;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.util.CommonUtil;
import org.hibernate.exception.DataException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassStudentServiceImpl extends BaseService implements ClassStudentService {
    private final ClassStudentRepo repo;
    private final ClassRepo classRepo;
    private final StudentRepo studentRepo;

    @Override
    public List<ClassStudentRes> get() {
        List<ClassStudentEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<ClassStudentRes> getById(String id) {
        ClassStudentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<ClassStudentRes> save(ClassStudentReq request) {
        if(repo.existsByClassIdAndStudentId(request.getKelasId(), request.getSiswaId())){
            log.info("Save KelasSiswa gagal, terjadi error : kode sudah digunakan");
            Map<String, String> errors = Map.of("siswaId", "kelasId "+ request.getKelasId() +" dan "+ request.getSiswaId()+" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        ClassStudentEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassStudentRes> save(String kelasId, List<ClassStudentReq> request) {
        if(!classRepo.existsById(kelasId)){
            log.info("Save KelasSiswa gagal, terjadi error : kelasId {} sudah digunakan", kelasId);
            Map<String, String> errors = Map.of("siswaId", MessageFormat.format("kelasId {} tidak ditemukan", kelasId));
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ClassStudentRes> update(ClassStudentReq request, String id) {
        ClassStudentEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassStudentRes> delete(String id) {
        ClassStudentEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);
        return saveOrUpdate(result);
    }

    private Optional<ClassStudentRes> saveOrUpdate(ClassStudentEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save KelasSiswa, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save KelasSiswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private ClassStudentEntity getEntityById(String id) {
        ClassStudentEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private ClassStudentRes convertEntityToRes(ClassStudentEntity entity){
        ClassStudentRes result = new ClassStudentRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private ClassStudentEntity convertReqToEntity(ClassStudentReq request){
        ClassStudentEntity result = new ClassStudentEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(ClassStudentReq request, ClassStudentEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
