/**
 * 
 */
package org.edupro.webapi.classes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.subject.model.SubjectEntity;

import java.io.Serializable;

/**
 * @author Awiyanto Ajisasongko
 *
 * Nov 30, 2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_class_subject")
public class ClassSubjectEntity extends BaseIdEntity implements Serializable {
	@Column(name = "class_id", nullable = false, length = 36)
	private String classId;

	@ManyToOne
	@JoinColumn(name = "class_id", insertable = false, updatable = false)
	private ClassEntity classEntity;
	
	@Column(name = "subject_id", nullable = false, length = 36)
	private String subjectId;

	@ManyToOne
	@JoinColumn(name = "MAPELID", insertable = false, updatable = false)
	private SubjectEntity subject;

	@Column(name = "kkm_score")
	private double kkmScore;
}
