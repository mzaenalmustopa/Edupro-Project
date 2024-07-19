/**
 * 
 */
package org.edupro.webapi.courses.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Menyimpan topic/section dari suatu course/classroom
 * 
 * 
 * @author Awiyanto Ajisasongko
 *
 * 6 Feb 2024
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_course_section")
public class CourseSectionEntity extends BaseIdEntity {
	@Column(name = "course_id", insertable = false, updatable = false)
	private String courseId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable = false)
	private CourseEntity course;

	@Column(name = "section_type", length = 20, nullable = false)
	private String sectionType;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "parent_id", length = 36, insertable = false, updatable = false)
	private String parentId;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private CourseSectionEntity parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<CourseSectionEntity> children = new ArrayList<>();

	@Column(name = "no_urut")
	private Integer noUrut;

	public CourseSectionEntity(String id, CourseEntity course, String sectionType, String name, Integer noUrut) {
		this.setId(id);
		this.course = course;
		this.sectionType = sectionType;
		this.name = name;
		this.noUrut = noUrut;
	}

	public CourseSectionEntity(String id, CourseEntity course, String sectionType, String name, Integer noUrut, CourseSectionEntity parent) {
		this.setId(id);
		this.course = course;
		this.sectionType = sectionType;
		this.name = name;
		this.noUrut = noUrut;
		this.parent = parent;
	}

	public void addChild(CourseSectionEntity courseSectionEntity) {
		this.children.add(courseSectionEntity);
		courseSectionEntity.setParent(this);
	}
}
