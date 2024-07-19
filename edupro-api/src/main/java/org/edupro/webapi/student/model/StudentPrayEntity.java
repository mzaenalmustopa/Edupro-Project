package org.edupro.webapi.student.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.lookup.model.LookupEntity;
import org.edupro.webapi.base.model.BaseIdEntity;

import java.time.LocalDate;

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
@Table(name = "t_class_pray")
public class StudentPrayEntity extends BaseIdEntity {
	@Column(name = "KLSSISWAID", nullable = false, length = 36)
	private String kelasSiswaId;

	@Column(name = "IBDID", nullable = false, length = 36)
	private String ibadahId;

	@ManyToOne
	@JoinColumn(name = "IBDID", insertable = false, updatable = false)
	private LookupEntity lookup;

	@Column(name = "IBDDate")
	private LocalDate tglIbadah;

	@Column(name = "IBDSTS", nullable = false, length = 20)
	private String ibadahStatus;

	@Column(name = "CATATAN", length = 100)
	private String catatan;

	@Column(name = "ISDONE")
	private Boolean isDone;
}
