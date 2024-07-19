/**
 * 
 */
package org.edupro.webapi.attachment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.Builder.Default;
import org.edupro.webapi.base.model.BaseIdEntity;

/**
 * @author Awiyanto Ajisasongko
 *
 * May 16, 2022
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_attachment")
public class AttachmentEntity extends BaseIdEntity {
	private static final long serialVersionUID = 4672155540988374323L;
	
	@Id
	@Column(name = "id", nullable = false, length = 36)
	private String id;
	
	@Column(name = "types", length = 100)
	private String contentType;
	
	@Column(name = "file_name", length = 100)
	private String filename;
	
	@Default
	@Column(name = "is_public")
	private boolean publiclyAccessible = true;
	
	@Column(name = "path")
	private String filePath;
	
	@Column(name = "size")
	private Long size;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "owner", length = 100)
	private String owner;
}
