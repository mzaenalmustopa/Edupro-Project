package org.edupro.webapi.classes.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.academic.model.AcademicSessionEntity;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.student.model.StudentEntity;

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
@Table(name = "t_class_student")
public class ClassStudentEntity extends BaseIdEntity {
	@Column(name = "academic_session_id", nullable = false, length = 36)
	private String academicSessionId;

	@ManyToOne
	@JoinColumn(name = "academic_session_id", insertable = false, updatable = false)
	private AcademicSessionEntity academicSession;

	@Column(name = "class_id", nullable = false, length = 36)
	private String classId;

	@ManyToOne
	@JoinColumn(name = "class_id", insertable = false, updatable = false)
	private ClassEntity classEntity;

	@Column(name = "student_id", nullable = false, length = 36)
	private String studentId;

	@ManyToOne
	@JoinColumn(name = "student_id", insertable = false, updatable = false)
	private StudentEntity studentEntity;

	@Column(name = "assignment_score")
	private double assignmentScore;

	@Column(name = "midterm_score")
	private double midtermScore;

	@Column(name = "end_of_score")
	private double endOfScore;

	@Column(name = "final_score")
	private double finalScore;

	@Column(name = "notes")
	private String notes;
}
