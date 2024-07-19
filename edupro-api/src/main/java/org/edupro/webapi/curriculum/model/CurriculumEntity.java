/**
 * 
 */
package org.edupro.webapi.curriculum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_curriculum")
public class CurriculumEntity extends BaseIdEntity {
	@Column(name = "code", length = 20)
	private String code;
	
	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "position")
	private Integer position;
}
