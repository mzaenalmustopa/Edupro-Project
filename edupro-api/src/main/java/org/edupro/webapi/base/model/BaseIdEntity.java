/**
 * 
 */
package org.edupro.webapi.base.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.util.CommonUtil;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

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
public abstract class BaseIdEntity extends BaseEntity {
	@Id
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@PrePersist
	public void onCreate(){
		this.setCreatedAt(LocalDateTime.now());
		this.setUpdatedAt(LocalDateTime.now());
		this.id = CommonUtil.getUUID();
		this.setStatus(DataStatus.ACTIVE);
	}

	@PreUpdate
	public void onUpdate(){
		this.setUpdatedAt(LocalDateTime.now());
	}

	@PreRemove
	public void onRemove(){
		this.setDeletedAt(LocalDateTime.now());
		this.setStatus(DataStatus.DELETED);
	}
}
