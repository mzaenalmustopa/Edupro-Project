package org.edupro.webapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.academic.repository.AcademicYearRepo;
import org.edupro.webapi.constant.Constant;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.courses.model.CourseEntity;
import org.edupro.webapi.courses.repository.CourseRepo;
import org.edupro.webapi.courses.model.CourseSectionEntity;
import org.edupro.webapi.courses.repository.CourseSectionRepo;
import org.edupro.webapi.curriculum.model.CurriculumEntity;
import org.edupro.webapi.curriculum.repository.CurriculumRepo;
import org.edupro.webapi.institution.model.InstitutionEntity;
import org.edupro.webapi.institution.repository.InstitutionRepo;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.level.repository.LevelRepo;
import org.edupro.webapi.lookup.model.LookupEntity;
import org.edupro.webapi.lookup.repository.LookupRepo;
import org.edupro.webapi.person.model.PersonEntity;
import org.edupro.webapi.person.repository.PersonRepo;
import org.edupro.webapi.student.model.StudentEntity;
import org.edupro.webapi.student.repository.StudentRepo;
import org.edupro.webapi.util.CommonUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final LookupRepo lookupRepo;
    private final InstitutionRepo institutionRepo;
    private final LevelRepo levelRepo;
    private final AcademicYearRepo taRepo;
    private final CurriculumRepo kurRepo;
    private final CourseRepo courseRepo;
    private final CourseSectionRepo sectionRepo;
    private final PersonRepo personRepo;
    private final StudentRepo studentRepo;

    @Override
    public void run(String... args) throws Exception {
        initLembaga();
        initResourceType();
        initAgama();
        initWargaNegara();
        initJenjangPendidikan();
        initSemester();
        initGender();
        initGolDarah();
        initAbsensi();
        initLevelKelas();
        initAttachmentType();
        initJenisIbadahOpsi();
        initJenisIbadahCheck();

        initLevelSD();
        initLevelSMP();

        initKurikulum();
        initAcademicYear();
        initCourse();

        initPerson();
        initSiswa();

        initGroup();
    }

    private void initGroup(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_MAIN);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_AGAMA,"Agama",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_WN,"Warga Negara",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_LEVEL_KELAS,"Level Kelas",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_IBADAH_OPSI,"Pilihan Ibadah",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_IBADAH_CHECK,"Checking Ibadah",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_ABSENSI,"Absensi",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_GENDER,"Gender",7),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_GOL_DARAH,"Golongan Darah",8),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_SEMESTER,"Semester",9),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_JENJANG_PENDIDIKAN,"Jenjang Pendidikan",10),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_PEKERJAAN,"Pekerjaan",11),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_RESOURCE_TYPE,"Resource Type",12),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_MAIN, Constant.GROUP_ATTACHMENT_TYPE,"Pekerjaan",13)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Jenjang Pendidikan is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initPerson(){
        if (personRepo.countByStatus(DataStatus.ACTIVE) > 0) {
            return;
        }

        List<PersonEntity> personList = Arrays.asList(
                new PersonEntity("P001","Awiyanto","18010101"),
                new PersonEntity("P002","Anton Baskoro","18010102"),
                new PersonEntity("P003","Akbar Nugroho","18010103"),
                new PersonEntity("P004","Roni Purwanto","18010104"),
                new PersonEntity("P005","Pandu Wicaksono","18010105"),
                new PersonEntity("P006","Zainal Arifin","18010106")
        );

        try{
            personRepo.saveAll(personList);
            log.info("Save person successfully");
        }catch (Exception e){
            log.error("Save person failed");
        }
    }

    private void initSiswa(){
        if(studentRepo.countByStatus(DataStatus.ACTIVE) > 0) {
            return;
        }

        List<StudentEntity> siswaList = Arrays.asList(
                new StudentEntity("Diktya", "110011001","PRIA"),
                new StudentEntity("Sabil", "110011002","PRIA"),
                new StudentEntity("Mustopa", "110011003","PRIA"),
                new StudentEntity("Umam", "110011004","PRIA"),
                new StudentEntity("Arif", "110011005","PRIA"),
                new StudentEntity("Siti Khoiriyah", "110011006","WANITA"),
                new StudentEntity("Siti Marfuah", "110011007","WANITA"),
                new StudentEntity("Siti Maimunah", "110011008","WANITA"),
                new StudentEntity("Siti Zubaidah", "110011008","WANITA"),
                new StudentEntity("Siti Aminah", "110011009","WANITA"),
                new StudentEntity("Siti Jamillah", "110011010","WANITA"),
                new StudentEntity("Siti Sumarsih", "110011011","WANITA"),
                new StudentEntity("Ahmad Mustolih", "110011012","PRIA"),
                new StudentEntity("Ahmad Syukur", "110011013","PRIA"),
                new StudentEntity("Ahmad Masykur", "110011014","PRIA"),
                new StudentEntity("Ahmad Subur", "110011015","PRIA"),
                new StudentEntity("Ahmad Ghofur", "110011016","PRIA"),
                new StudentEntity("Ahmad Yusron", "110011017","PRIA"),
                new StudentEntity("Sugeng Fitriyadi", "110011018","PRIA"),
                new StudentEntity("Sugeng Rawuh", "110011019","PRIA"),
                new StudentEntity("Sugeng Riyadi", "110011020","PRIA"),
                new StudentEntity("Sugeng Waras", "110011021","PRIA"),
                new StudentEntity("Sugeng Tindak", "110011022","PRIA"),
                new StudentEntity("Sugeng Dahar", "110011023","PRIA"),
                new StudentEntity("Sugeng Sare", "110011024","PRIA"),
                new StudentEntity("Sugeng Makaryo", "110011025","PRIA")
        );

        try{
            studentRepo.saveAll(siswaList);
            log.info("Save siswa successfully");
        }catch (Exception e){
            log.error("Save siswa failed");
        }
    }

    private void initLembaga(){
        if(!institutionRepo.existsByName("SDIT")){
            InstitutionEntity institutionEntity = new InstitutionEntity();
            institutionEntity.setName("SDIT");
            institutionEntity.setCode("SDIT");
            institutionEntity.setShortName("SDIT");

            try {
                institutionRepo.save(institutionEntity);
                log.info("Save SDIT is successful");
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }

        if(!institutionRepo.existsByName("SMPIT")){
            InstitutionEntity institutionEntity = new InstitutionEntity();
            institutionEntity.setName("SMPIT");
            institutionEntity.setCode("SMPIT");
            institutionEntity.setShortName("SMPIT");

            try {
                institutionRepo.save(institutionEntity);
                log.info("Save SMPIT is successful");
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }

    private void initJenjangPendidikan(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_JENJANG_PENDIDIKAN);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_JENJANG_PENDIDIKAN, "TK","Taman Kanak-Kanak",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_JENJANG_PENDIDIKAN, "SD","Sekolah Dasar",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_JENJANG_PENDIDIKAN, "SMP","Sekolah Menengah Pertama",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_JENJANG_PENDIDIKAN, "SMA","Sekolah Menengah Atas",4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Jenjang Pendidikan is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initResourceType(){
        int count = lookupRepo.countAllByGroup(Constant.ResourceType.TYPE);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.MATERIAL,Constant.ResourceType.MATERIAL,1),
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.QUESTION,Constant.ResourceType.QUESTION,2),
                new LookupEntity(CommonUtil.getUUID(), Constant.ResourceType.TYPE, Constant.ResourceType.QUIZ,Constant.ResourceType.QUIZ,3)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save ResourceType is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initAgama(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_AGAMA);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "ISLAM","Islam",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KRISTEN","Kristen",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KATHOLIK","Katholik",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "HINDU","Hindu",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "BUDHA","Budha",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KONG_HU_CHU","Kong Hu Chu",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "KEPERCAYAAN","Kepercayaan",7),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_AGAMA, "LAINNYA","Lainnya",8)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Agama is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initWargaNegara(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_WN);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_WN, "WNI","Warga Negara Indonesia",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_WN, "WNA","Warga Negara Asing",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Warga Negara is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initSemester(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_SEMESTER);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_SEMESTER, "1","Semester Ganjil",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_SEMESTER, "2","Semester Genap",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Semester is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initGender(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_GENDER);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GENDER, "PRIA","Pria",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GENDER, "WANITA","Wanita",2)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Gender is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initGolDarah(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_GOL_DARAH);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "A","A",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "B","B",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "AB","AB",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_GOL_DARAH, "O","O",4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Gol Darah is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initAbsensi(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_ABSENSI);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "HADIR","Hadir",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "IJIN","Ijin",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "SAKIT","Sakit",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_ABSENSI, "ALPHA","Alpha",4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Absendi is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initAttachmentType(){
        int count = lookupRepo.countAllByGroup(Constant.AttachmentType.TYPE);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.DRIVE,Constant.AttachmentType.DRIVE,1),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.YOUTUBE,Constant.AttachmentType.YOUTUBE,2),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.UPLOAD_FILE,Constant.AttachmentType.UPLOAD_FILE,3),
                new LookupEntity(CommonUtil.getUUID(), Constant.AttachmentType.TYPE, Constant.AttachmentType.LINK,Constant.AttachmentType.LINK,4)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Attachment Type is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initLevelKelas(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_LEVEL_KELAS);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "TK_01","TK Tahun 1",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "TK_02","TK Tahun 2",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_01","SD Kelas 1",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_02","SD Kelas 2",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_03","SD Kelas 3",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_04","SD Kelas 4",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_05","SD Kelas 5",7),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SD_06","SD Kelas 6",8),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_07","SMP Kelas 7",9),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_08","SMP Kelas 8",10),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMP_09","SMP Kelas 9",11),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_10","SMA Kelas 10",12),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_11","SMA Kelas 11",13),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_LEVEL_KELAS, "SMA_12","SMA Kelas 12",14)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Level Kelas is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initJenisIbadahOpsi(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_IBADAH_OPSI);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_DHUHA","Sholat Dhuha",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "MUROJAAH_PAGI","Murojaah Pagi",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "MAJELIS_PAGI","Majelis Pagi",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_ZHUHUR","Sholat Zhuhur",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "LITERASI_SIANG","Literasi Siang",5),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "SHOLAT_ASHAR","Sholat Ashar",6),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "LITERASI_SORE","Literasi Sore",7)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Ibadah Opsi is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initJenisIbadahCheck(){
        int count = lookupRepo.countAllByGroup(Constant.GROUP_IBADAH_CHECK);
        if(count > 0){
            return;
        }

        List<LookupEntity> lookupEntities = List.of(
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_CHECK, "IBADAH_CHECK_01","Memimpin Sholat Dhuha dan Doa",1),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_02","Memimpin Al Ma tsurat",2),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_03","Memimpin Tilawah /  Murojaah",3),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_04","Terakhir Murojaah",4),
                new LookupEntity(CommonUtil.getUUID(), Constant.GROUP_IBADAH_OPSI, "IBADAH_CHECK_05","Pendamping",5)
        );

        try {
            lookupRepo.saveAll(lookupEntities);
            log.info("Save Ibadah Check is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initLevelSD(){
        InstitutionEntity sdit = institutionRepo.findByName("SDIT").orElse(null);
        if(sdit == null){
            return;
        }

        int count = levelRepo.countAllByInstitutionId(sdit.getId());
        if(count > 0){
            return;
        }

        List<LevelEntity> levelEntities = List.of(
                new LevelEntity(sdit.getId(), "SD_01", "Kelas 1", 1),
                new LevelEntity(sdit.getId(), "SD_02", "Kelas 2", 2),
                new LevelEntity(sdit.getId(), "SD_03", "Kelas 3", 3),
                new LevelEntity(sdit.getId(), "SD_04", "Kelas 4", 4),
                new LevelEntity(sdit.getId(), "SD_05", "Kelas 5", 5),
                new LevelEntity(sdit.getId(), "SD_06", "Kelas 6", 6)
        );

        try {
            levelRepo.saveAll(levelEntities);
            log.info("Save level sd is successful");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void initLevelSMP(){
        InstitutionEntity smpit = institutionRepo.findByName("SMPIT").orElse(null);
        if(smpit == null){
            return;
        }
        int count = levelRepo.countAllByInstitutionId(smpit.getId());
        if(count > 0 ){
            return;
        }

        List<LevelEntity> levelEntities = List.of(
                new LevelEntity(smpit.getId(), "SMP_07","Kelas 7",1),
                new LevelEntity(smpit.getId(), "SMP_08","Kelas 8",2),
                new LevelEntity(smpit.getId(), "SMP_09","Kelas 9",3)
        );

        try {
            levelRepo.saveAll(levelEntities);
            log.info("Save level smp is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initKurikulum(){
        int count = kurRepo.countAllByStatus(DataStatus.ACTIVE);
        if(count > 0){
            return;
        }

        List<CurriculumEntity> kurikulum = List.of(
                new CurriculumEntity("KURIKULUM_1947","Kurikulum Rencana Pelajaran (1947)",1),
                new CurriculumEntity("KURIKULUM_1953","Kurikulum Rencana Pelajaran Terurai (1952)",2),
                new CurriculumEntity("KURIKULUM_1964","Kurikulum 1964",3),
                new CurriculumEntity("KURIKULUM_1968","Kurikulum 1968",4),
                new CurriculumEntity("KURIKULUM_1975","Kurikulum 1975",5),
                new CurriculumEntity("KURIKULUM_1984","Kurikulum 1984",6),
                new CurriculumEntity("KURIKULUM_1994","Kurikulum 1994 dan Suplemen Kurikulum 1999",7),
                new CurriculumEntity("KURIKULUM_2004","Kurikulum 2004",8),
                new CurriculumEntity("KURIKULUM_2006","Kurikulum 2006",9),
                new CurriculumEntity("KURIKULUM_2013","Kurikulum 2013",10)
        );

        try {
            kurRepo.saveAll(kurikulum);
            log.info("Save Kurikulum is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initAcademicYear(){
        int count = taRepo.countByStatus(DataStatus.ACTIVE);
        if(count > 0){
            return;
        }

        CurriculumEntity kurikulum = kurRepo.findByCode("KURIKULUM_2013").orElse(null);
        if(kurikulum == null){
            return;
        }

        int curYear = LocalDate.now().getYear();
        List<AcademicYearEntity> tahunAjaran = new ArrayList<>();
        for (int i = curYear-8; i <= curYear+2; i++) {
            String nama = i +" - "+ (i+1);
            LocalDate startDate = LocalDate.of(i,6,1);
            LocalDate endDate = LocalDate.of(i+1,5,31);
            tahunAjaran.add(new AcademicYearEntity(nama,kurikulum.getId(),startDate, endDate));
        }

        try {
            taRepo.saveAll(tahunAjaran);
            log.info("Save Tahun Ajaran is successful");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    private void initCourse(){
        if(courseRepo.countAllByStatus(DataStatus.ACTIVE) > 0){
            return;
        }

        CourseEntity course1 = new CourseEntity("Matematika","Mata pelajaran Matematika","MTK",LocalDate.now().minusMonths(6),LocalDate.now().plusMonths(2),"edupro.user");
        course1.setId(CommonUtil.getUUID());
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 1",1));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 2",2));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 3",3));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 4",4));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 5",5));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 6",6));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 7",7));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 8",8));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 9",9));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 10",10));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 11",11));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 12",12));
        course1.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course1,"TOPIC","Topik Matematika 13",13));

        try {
            courseRepo.saveAndFlush(course1);
            log.info("Save Course 1 is successful");
            initSection();
        }catch (Exception e){
            log.error("Save Course 1 error: {}",e.getMessage());
        }

        CourseEntity course2 = new CourseEntity("Bahasa Indonesia","Mata pelajaran Bahasa Indonesia","MTK",LocalDate.now().minusMonths(6),LocalDate.now().plusMonths(2),"edupro.user");
        course2.setId(CommonUtil.getUUID());
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 1",1));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 2",2));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 3",3));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 4",4));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 5",5));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 6",6));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 7",7));
        course2.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course2,"TOPIC","Topik Bhs Indonesia 8",8));

        try {
            courseRepo.saveAndFlush(course2);
            log.info("Save Course 2 is successful");
        }catch (Exception e){
            log.error("Save Course 2 error: {}",e.getMessage());
        }

        CourseEntity course3 = new CourseEntity("Bahasa Inggris","Mata pelajaran Bahasa Inggris","MTK",LocalDate.now().minusMonths(6),LocalDate.now().plusMonths(2),"edupro.user");
        course3.setId(CommonUtil.getUUID());
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 1",1));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 2",2));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 3",3));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 4",4));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 5",5));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 6",6));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 7",7));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 8",8));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 9",9));
        course3.addCourseSection(new CourseSectionEntity(CommonUtil.getUUID(),course3,"TOPIC","Topik Bhs Inggris 10",10));

        try {
            courseRepo.saveAndFlush(course3);
            log.info("Save Course 3 is successful");
        }catch (Exception e){
            log.error("Save Course 3 error: {}",e.getMessage());
        }
    }

    private void initSection(){

        CourseSectionEntity section1 = sectionRepo.findAllByName("Topik Matematika 1").get(0);
        section1.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section1.getCourse(),"SUB-TOPIC","Sub Topik Matematika 1.1", 1, section1));
        section1.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section1.getCourse(),"SUB-TOPIC","Sub Topik Matematika 1.2", 2, section1));
        section1.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section1.getCourse(),"SUB-TOPIC","Sub Topik Matematika 1.3", 3, section1));
        section1.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section1.getCourse(),"SUB-TOPIC","Sub Topik Matematika 1.4", 4, section1));

        try {
            sectionRepo.saveAndFlush(section1);
            log.info("Save Section 1 is successful");
        }catch (Exception e){
            log.error("Save Section 1 error: {}",e.getMessage());
        }

        CourseSectionEntity section2 = sectionRepo.findAllByName("Topik Matematika 2").get(0);
        section2.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section2.getCourse(),"SUB-TOPIC","Sub Topik Matematika 2.1",1, section2));
        section2.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section2.getCourse(),"SUB-TOPIC","Sub Topik Matematika 2.2",2, section2));
        section2.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section2.getCourse(),"SUB-TOPIC","Sub Topik Matematika 2.3",3, section2));
        section2.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section2.getCourse(),"SUB-TOPIC","Sub Topik Matematika 2.4",4, section2));
        section2.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section2.getCourse(),"SUB-TOPIC","Sub Topik Matematika 2.5",5, section2));

        try {
            sectionRepo.saveAndFlush(section2);
            log.info("Save Section 2 is successful");
        }catch (Exception e){
            log.error("Save Section 2 error: {}",e.getMessage());
        }

        CourseSectionEntity section3 = sectionRepo.findAllByName("Topik Matematika 3").get(0);
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.1",1, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.2",2, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.3",3, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.4",4, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.5",5, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.6",6, section3));
        section3.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section3.getCourse(),"SUB-TOPIC","Sub Topik Matematika 3.7",7, section3));

        try {
            sectionRepo.saveAndFlush(section3);
            log.info("Save Section 3 is successful");
        }catch (Exception e){
            log.error("Save Section 3 error: {}",e.getMessage());
        }

        CourseSectionEntity section4 = sectionRepo.findAllByName("Topik Matematika 4").get(0);
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.1",1,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.2",2,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.3",3,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.4",4,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.5",5,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.6",6,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.7",7,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.8",8,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.9",9,section4));
        section4.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section4.getCourse(),"SUB-TOPIC","Sub Topik Matematika 4.10",10,section4));

        try {
            sectionRepo.saveAndFlush(section4);
            log.info("Save Section 4 is successful");
        }catch (Exception e){
            log.error("Save Section 4 error: {}",e.getMessage());
        }

        CourseSectionEntity section5 = sectionRepo.findAllByName("Topik Matematika 5").get(0);
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.1", 1, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.2", 2, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.3", 3, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.4", 4, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.5", 5, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.6", 6, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.7", 7, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.8", 8, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.9", 9, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.10", 10, section5));
        section5.addChild(new CourseSectionEntity(CommonUtil.getUUID(),section5.getCourse(),"SUB-TOPIC","Sub Topik Matematika 5.11", 11, section5));

        try {
            sectionRepo.saveAndFlush(section5);
            log.info("Save Section 5 is successful");
        }catch (Exception e){
            log.error("Save Section 5 error: {}",e.getMessage());
        }
    }
}
