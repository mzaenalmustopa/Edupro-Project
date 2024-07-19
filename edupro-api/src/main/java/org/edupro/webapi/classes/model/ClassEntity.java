package org.edupro.webapi.classes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.academic.model.AcademicYearEntity;
import org.edupro.webapi.institution.model.InstitutionEntity;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.building.model.RoomEntity;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.person.model.PersonEntity;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_class")
public class ClassEntity extends BaseIdEntity {
	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "room_id", nullable = false, length = 36)
	private String roomId;

	@ManyToOne
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	private RoomEntity room;

	@Column(name = "institution_id", nullable = false, length = 36)
	private String institutionId;

	@ManyToOne
	@JoinColumn(name = "institution_id", insertable = false, updatable = false)
	private InstitutionEntity institution;

	@Column(name = "academic_year_id", nullable = false)
	private String academicYearId;

	@ManyToOne
	@JoinColumn(name = "academic_year_id", insertable = false, updatable = false)
	private AcademicYearEntity academicYear;

	@Column(name = "level_id", nullable = false, length = 36)
	private String levelId;

	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private LevelEntity level;

	@Column(name = "academic_session_id", nullable = false, length = 36)
	private String academicSessionId;

	@ManyToOne
	@JoinColumn(name = "academic_session_id", insertable = false, updatable = false)
	private AcademicSessionEntity academicSession;

	@Column(name = "homeroom_teacher_id", nullable = false, length = 36)
	private String homeroomTeacherId;

	@ManyToOne
	@JoinColumn(name = "homeroom_teacher_id", insertable = false, updatable = false)
	private PersonEntity homeroomTeacher;
}
