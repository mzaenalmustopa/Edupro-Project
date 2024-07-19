/**
 * 
 */
package org.edupro.webapi.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @author Awiyanto Ajisasongko
 *
 * Aug 24, 2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SoftDelete(strategy = SoftDeleteType.DELETED)
public abstract class BaseEntity {
	@Column(name = "created_by", length = 100)
	@CreatedBy
	private String createdBy;

	@Column(name = "updated_by", length = 100)
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "deleted_by", length = 100)
	private String deletedBy;

	@Column(name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	@Column(name = "status", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private DataStatus status = DataStatus.ACTIVE;
}
