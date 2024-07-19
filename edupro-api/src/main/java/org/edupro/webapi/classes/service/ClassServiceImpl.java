package org.edupro.webapi.classes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.academic.repository.AcademicSessionRepo;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.academic.repository.AcademicYearRepo;
import org.edupro.webapi.building.model.RoomEntity;
import org.edupro.webapi.building.repository.RoomRepo;
import org.edupro.webapi.classes.model.ClassEntity;
import org.edupro.webapi.classes.model.ClassReq;
import org.edupro.webapi.classes.model.ClassRes;
import org.edupro.webapi.classes.repository.ClassRepo;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.institution.model.InstitutionEntity;
import org.edupro.webapi.institution.repository.InstitutionRepo;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.level.repository.LevelRepo;
import org.edupro.webapi.person.model.PersonEntity;
import org.edupro.webapi.person.repository.PersonRepo;
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
public class ClassServiceImpl extends BaseService implements ClassService {
    private final ClassRepo repo;
    private final RoomRepo roomRepo;
    private final InstitutionRepo institutionRepo;
    private final AcademicSessionRepo academicSessionRepo;
    private final PersonRepo personRepo;
    private final AcademicYearRepo academicYearRepo;
    private final LevelRepo levelRepo;

    @Override
    public List<ClassRes> get() {
        List<ClassEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<ClassRes> getById(String id) {
        ClassEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<ClassRes> save(ClassReq request) {
        if(repo.existsByCode(request.getCode())){
            log.info("Save Kelas gagal, terjadi error : id sudah digunakan");
            Map<String, String> errors = Map.of("kode", "Kode "+ request.getCode() +" sudah digunakan");
            throw new EduProApiException("Save gagal", HttpStatus.BAD_REQUEST, errors);
        }

        ClassEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassRes> update(ClassReq request, String id) {
        ClassEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<ClassRes> delete(String id) {
        ClassEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);
        return saveOrUpdate(result);
    }

    private Optional<ClassRes> saveOrUpdate(ClassEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Kelas, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Kelas gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private ClassEntity getEntityById(String id) {
        ClassEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private ClassRes convertEntityToRes(ClassEntity entity){
        ClassRes result = new ClassRes();
        BeanUtils.copyProperties(entity, result);
        result.setStatus(entity.getStatus());

        if (entity.getRoom() != null){
            result.setRoomId(entity.getRoom().getId());
            result.setRoomCode(entity.getRoom().getCode());
        }

        if (entity.getInstitution() != null){
            result.setInstitutionId(entity.getInstitution().getId());
            result.setInstitutionName(entity.getInstitution().getName());
        }

        if (entity.getAcademicYear() != null){
            result.setAcademicYearId(entity.getAcademicYear().getId());
            result.setAcademicYearName(entity.getAcademicYear().getName());
        }

        if (entity.getLevel() != null){
            result.setLevelId(entity.getLevel().getId());
            result.setLevelName(entity.getLevel().getName());
        }

        if (entity.getAcademicSession() != null){
            result.setAcademicSessionId(entity.getAcademicSession().getId());
            result.setSemester(entity.getAcademicSession().getSemester());
        }

        if (entity.getHomeroomTeacher() != null){
            result.setHomeroomTeacherId(entity.getHomeroomTeacher().getId());
            result.setHomeroomTeacherName(entity.getHomeroomTeacher().getFullName());
        }
        return result;
    }

    private ClassEntity convertReqToEntity(ClassReq request){
        RoomEntity ruangan = roomRepo.findById(request.getRoomId()).orElse(null);
        if(ruangan == null){
            Map<String, String> errors = Map.of("ruangId", "ruangId "+ request.getRoomId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        InstitutionEntity lembaga = institutionRepo.findById(request.getInstitutionId()).orElse(null);
        if(lembaga == null){
            Map<String, String> errors = Map.of("lembagaId", "lembagaId "+ request.getInstitutionId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicSessionEntity sesiAkademik = academicSessionRepo.findById(request.getAcademicSessionId()).orElse(null);
        if(sesiAkademik == null){
            Map<String, String> errors = Map.of("sesiAkademikId", "sesiAkademikId "+ request.getAcademicSessionId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        PersonEntity person = personRepo.findById(request.getHomeroomTeacherId()).orElse(null);
        if(person == null){
            Map<String, String> errors = Map.of("waliKelasId", "waliKelasId "+ request.getHomeroomTeacherId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        AcademicYearEntity tahunAjaran = academicYearRepo.findById(request.getAcademicYearId()).orElse(null);
        if(tahunAjaran == null){
            Map<String, String> errors = Map.of("tahunAjaranId", "tahunAjaranId" + request.getAcademicYearId()+ " tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        LevelEntity level = levelRepo.findById(request.getLevelId()).orElse(null);
        if(level == null){
            Map<String, String> errors = Map.of("levelId", "levelId "+ request.getLevelId() +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        ClassEntity result = new ClassEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(ClassReq request, ClassEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        String userId = this.getUserInfo().getUserId();
        if(!userId.isEmpty()){
            result.setCreatedBy(userId);
            result.setUpdatedBy(userId);
        }
    }
}
