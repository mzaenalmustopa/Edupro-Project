package org.edupro.webapi.courses.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.constant.Formatter;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.courses.model.*;
import org.edupro.webapi.courses.repository.CoursePersonRepo;
import org.edupro.webapi.courses.repository.CourseRepo;
import org.edupro.webapi.courses.repository.CourseStudentRepo;
import org.edupro.webapi.exception.EduProApiException;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.level.repository.LevelRepo;
import org.edupro.webapi.person.model.PersonEntity;
import org.edupro.webapi.person.repository.PersonRepo;
import org.edupro.webapi.base.service.BaseService;
import org.edupro.webapi.student.model.StudentEntity;
import org.edupro.webapi.student.repository.StudentRepo;
import org.edupro.webapi.subject.model.SubjectEntity;
import org.edupro.webapi.subject.repository.SubjectRepo;
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
public class CourseServiceImpl extends BaseService implements CourseService {
    private final SubjectRepo subjectRepo;
    private final CourseRepo repo;
    private final CoursePersonRepo coursePersonRepo;
    private final CourseStudentRepo courseStudentRepo;
    private final StudentRepo studentRepo;
    private final PersonRepo personRepo;
    private final LevelRepo levelRepo;

    @Override
    public List<CourseRes> get() {
        List<CourseEntity> result = this.repo.findAllByStatus(DataStatus.ACTIVE);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public List<CourseRes> getByUserId(String userId) {
        List<CourseEntity> result = this.repo.findAllByStatusAndCreatedBy(DataStatus.ACTIVE, userId);
        if(result.isEmpty()){
            return Collections.emptyList();
        }
        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CoursePeopleRes> getPeopleById(String id) {
        CoursePeopleRes result = new CoursePeopleRes(id);

        List<CourseStudentEntity> students = courseStudentRepo.findAllByCourseIdAndStatus(id, DataStatus.ACTIVE);
        if(!students.isEmpty()){
            List<CourseStudentRes> collect = students.stream().map(this::convertEntityToRes).toList();
            result.setStudents(collect);
        }

        List<CoursePersonEntity> persons = coursePersonRepo.findAllByCourseIdAndStatus(id, DataStatus.ACTIVE);
        if(!persons.isEmpty()){
            List<CoursePersonRes> collect = persons.stream().map(this::convertEntityToRes).toList();
            result.setTeachers(collect);
        }

        return Optional.of(result);
    }

    private CourseStudentRes convertEntityToRes(CourseStudentEntity entity) {
        CourseStudentRes res = new CourseStudentRes();
        BeanUtils.copyProperties(entity, res);
        if(entity.getStudent() != null){
            res.setSiswaName(entity.getStudent().getName());
        }
        return res;
    }

    @Override
    public Optional<CourseStudentRes> saveSiswa(String id, CourseStudentReq request) {
        CourseEntity course  = this.getEntityById(request.getCourseId());
        StudentEntity siswa = this.studentRepo.findById(request.getSiswaId()).orElse(null);
        if(siswa == null){
            return Optional.empty();
        }

        CourseStudentEntity entity = new CourseStudentEntity(course, siswa);
        try{
            courseStudentRepo.saveAndFlush(entity);
            return Optional.of(convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save Course Siswa gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public List<CourseStudentRes> saveSiswaList(List<CourseStudentReq> request) {
        return List.of();
    }

    private CoursePersonRes convertEntityToRes(CoursePersonEntity entity) {
        CoursePersonRes res = new CoursePersonRes();
        BeanUtils.copyProperties(entity, res);
        if(entity.getPerson() != null){
            res.setPersonName(entity.getPerson().getFullName());
        }
        return res;
    }

    @Override
    public Optional<CoursePersonRes> savePerson(String id, CoursePersonReq request) {
        CourseEntity course  = this.getEntityById(request.getCourseId());
        PersonEntity person = this.personRepo.findById(request.getPersonId()).orElse(null);
        if(person == null){
            return Optional.empty();
        }

        CoursePersonEntity entity = new CoursePersonEntity(course, person);
        try{
            coursePersonRepo.saveAndFlush(entity);
            return Optional.of(convertEntityToRes(entity));
        }catch (DataIntegrityViolationException e){
            log.error("Save Course Person gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    @Override
    public List<CoursePersonRes> savePersonList(List<CoursePersonReq> request) {
        return List.of();
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToResWithSection(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.convertReqToEntity(request);
        result.setId(CommonUtil.getUUID());
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.getEntityById(id);

        convertReqToEntity(request, result);
        return saveOrUpdate(result);
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        result.setDeletedAt(LocalDateTime.now());
        result.setStatus(DataStatus.DELETED);

        if(!this.getUserInfo().getUserId().isEmpty()){
            result.setCreatedBy(this.getUserInfo().getUserId());
        }
        return saveOrUpdate(result);
    }

    private Optional<CourseRes> saveOrUpdate(CourseEntity result) {
        try{
            this.repo.saveAndFlush(result);
            return Optional.of(this.convertEntityToRes(result));
        }catch (DataIntegrityViolationException e){
            log.error("Save Course, SQL error : {}", e.getMessage());
            DataException exception = (DataException) e.getCause();
            Map<String, String> errors = Map.of("sql", exception.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }catch (Exception e){
            log.error("Save Course gagal, terjadi error : {}", e.getMessage());
            Map<String, String> errors = Map.of("sql", e.getCause().getMessage());
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.MULTI_STATUS, errors);
        }
    }

    private CourseEntity getEntityById(String id) {
        CourseEntity result = this.repo.findById(id).orElse(null);
        if(result == null) {
            Map<String, String> errors = Map.of("kode", "Kode "+ id +" tidak dapat ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        return result;
    }

    private CourseRes convertEntityToRes(CourseEntity entity){
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getSubject() != null){
            result.setSubjectId(entity.getSubject().getId());
            result.setSubjectName(entity.getSubject().getName());
        }

        if (entity.getLevel() != null){
            result.setLevelId(entity.getLevel().getId());
            result.setLevelName(entity.getLevel().getName());
        }

        if (entity.getStartDate() != null){
            result.setStartDate(CommonUtil.toString(entity.getStartDate(), Formatter.DATE_FORMAT));
        }

        if (entity.getEndDate() != null){
            result.setEndDate(CommonUtil.toString(entity.getEndDate(), Formatter.DATE_FORMAT));
        }
        return result;
    }

    private CourseRes convertEntityToResWithSection(CourseEntity entity){
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);

        if(!entity.getCourseSectionList().isEmpty()){
            List<CourseSectionRes> sectionResList = entity.getCourseSectionList().stream().map(this::convertEntityToSectionRes).toList();
            result.setSections(sectionResList);
        }

        if (entity.getSubject() != null){
            result.setSubjectId(entity.getSubject().getId());
            result.setSubjectName(entity.getSubject().getName());
        }

        if (entity.getLevel() != null){
            result.setLevelId(entity.getLevel().getId());
            result.setLevelName(entity.getLevel().getName());
        }

        if (entity.getStartDate() != null){
            result.setStartDate(CommonUtil.toString(entity.getStartDate(), Formatter.DATE_FORMAT));
        }

        if (entity.getEndDate() != null){
            result.setEndDate(CommonUtil.toString(entity.getEndDate(), Formatter.DATE_FORMAT));
        }

        return result;
    }

    private CourseSectionRes convertEntityToSectionRes(CourseSectionEntity entity){
        CourseSectionRes result = new CourseSectionRes();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private CourseEntity convertReqToEntity(CourseReq request){
        SubjectEntity subject = this.subjectRepo.findById(request.getSubjectId()).orElse(null);
        if(subject == null){
            Map<String, String> errors = Map.of("mapelId", "mapelId" + request.getSubjectId() + " tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        LevelEntity level = this.levelRepo.findById(request.getLevelId()).orElse(null);
        if(level == null){
            Map<String, String> errors = Map.of("levelId", "levelId" + request.getLevelId() + " tidak ditemukan");
            throw new EduProApiException(MessageApp.FAILED, HttpStatus.BAD_REQUEST, errors);
        }

        CourseEntity result = new CourseEntity();
        BeanUtils.copyProperties(request, result);
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        return result;
    }

    private void convertReqToEntity(CourseReq request, CourseEntity result){
        BeanUtils.copyProperties(request, result);
        result.setUpdatedAt(LocalDateTime.now());

        if(!this.getUserInfo().getUserId().isEmpty()){
            result.setCreatedBy(this.getUserInfo().getUserId());
            result.setUpdatedBy(this.getUserInfo().getUserId());
        }
    }
}
