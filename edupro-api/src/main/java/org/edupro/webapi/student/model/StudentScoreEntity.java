/**
 * 
 */
package org.edupro.webapi.student.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.person.model.PersonEntity;
import org.edupro.webapi.subject.model.SubjectEntity;

import java.io.Serializable;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_nilai_siswa")
public class StudentScoreEntity extends BaseIdEntity implements Serializable {
	@Column(name = "tahun_ajaran_id", nullable = false, length = 36)
	private String tahunAjaranId;

	@ManyToOne
	@JoinColumn(name = "tahun_ajaran_id", insertable = false, updatable = false)
	private AcademicYearEntity tahunAjaran;

	@Column(name = "sesi_akademik", nullable = false)
	private Integer urut; // 1 = ganjil, 2 = genap

	@Column(name = "mapel_id", nullable = false, length = 36)
	private String mapelId;

	@ManyToOne
	@JoinColumn(name = "mapel_id", insertable = false, updatable = false)
	private SubjectEntity mapel;

	@Column(name = "level_id", length = 36, nullable = false)
	private String levelId;

	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private LevelEntity level;

	@Column(name = "person_id", nullable = false)
	private String pengampuId;

	@ManyToOne
	@JoinColumn(name = "person_id", insertable = false, updatable = false)
	private PersonEntity person;

	@Column(name = "siswa_id", nullable = false, length = 36)
	private String siswaId;

	@ManyToOne
	@JoinColumn(name = "siswa_id", insertable = false, updatable = false)
	private StudentEntity siswa;

	@Column(name = "nilai_tugas")
	private double nilaiTugas;

	@Column(name = "nilai_uts")
	private double nilaiUTS;

	@Column(name = "nilai_uas")
	private double nilaiUAS;

	@Column(name = "nilai_akhir")
	private double nilaiAkhir;

	@Column(name = "notes")
	private String catatan;
}
