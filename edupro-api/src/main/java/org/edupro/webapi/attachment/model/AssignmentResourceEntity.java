/**
 * 
 */
package org.edupro.webapi.attachment.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_attach_resource")
public class AssignmentResourceEntity implements Serializable {
	@Id
	@Column(name = "id", nullable = false, length = 36)
	private String id;

	@Column(name = "attachment_id", nullable = false, length = 36)
	private String attachmentId;

	@ManyToOne
	@JoinColumn(name = "attachment_id", insertable = false, updatable = false)
	private AttachmentEntity attachment;

	@Column(name = "instruction")
	private String instruction;
}
