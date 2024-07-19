/**
 * 
 */
package org.edupro.webapi.praying.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_pray")
public class PrayEntity extends BaseIdEntity {
	@Column(name = "code", length = 10, nullable = false)
	private String code;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "types", length = 36, nullable = false)
	private String types;
}
