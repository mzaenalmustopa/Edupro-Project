/**
 * 
 */
package org.edupro.webapi.praying.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "t_quote")
public class QuoteEntity extends BaseIdEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "value", nullable = false, columnDefinition = "TEXT")
	private String value;

	@Column(name = "types", length = 36, nullable = false)
	private String types;

	@Column(name = "source", length = 150, nullable = false)
	private String source;

	@Column(name = "position")
	private Integer position;
}
