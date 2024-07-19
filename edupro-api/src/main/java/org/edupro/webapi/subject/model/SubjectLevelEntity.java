/**
 * 
 */
package org.edupro.webapi.subject.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.institution.model.InstitutionEntity;
import org.edupro.webapi.level.model.LevelEntity;
import org.edupro.webapi.base.model.BaseIdEntity;

/**
 * Master data mapel per level
 * 
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
@Table(name = "t_subject_level")
public class SubjectLevelEntity extends BaseIdEntity {
	@Column(name = "institution_id", length = 36, nullable = false)
	private String institutionId;

	@ManyToOne
	@JoinColumn(name = "institution_id", insertable = false, updatable = false)
	private InstitutionEntity institution;

	@Column(name = "level_id", length = 36, nullable = false)
	private String levelId;

	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private LevelEntity level;

	@Column(name = "mapel_id", length = 36, nullable = false)
	private String mapelId;

	@ManyToOne
	@JoinColumn(name = "mapel_id", insertable = false, updatable = false)
	private SubjectEntity mapel;

	/**
	 * 0 = semua sesi (ganjil dan genap)
	 * 1 = semester ganjil saja
	 * 2 = semester genap saja
	 */
	@Column(name = "sesi_akademik")
	private Integer noUruSesiAkademik;
	
	@Column(name = "nilai_kkm")
	private double nilaiKKM;
}
