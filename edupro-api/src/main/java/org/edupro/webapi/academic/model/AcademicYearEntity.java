/**
 * 
 */
package org.edupro.webapi.academic.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.curriculum.model.CurriculumEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_academic_year")
public class AcademicYearEntity extends BaseIdEntity {
	private static final long serialVersionUID = 6154593684680418364L;
	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "curriculum_id", length = 36, nullable = false)
	private String curriculumId;

	@ManyToOne
	@JoinColumn(name = "curriculum_id", insertable = false, updatable = false)
	private CurriculumEntity curriculum;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;

	@OneToMany(mappedBy = "academicYear")
	private List<AcademicSessionEntity> sessionList = new ArrayList<>();

	public AcademicYearEntity(String name, String curriculumId, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.curriculumId = curriculumId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
