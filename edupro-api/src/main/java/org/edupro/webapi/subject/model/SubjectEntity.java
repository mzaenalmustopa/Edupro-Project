/**
 * 
 */
package org.edupro.webapi.subject.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

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
@Table(name = "t_subject")
public class SubjectEntity extends BaseIdEntity {
	@Column(name = "code", length = 10, nullable = false)
	private String code;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "types", length = 36, nullable = false)
	private String types;
}
