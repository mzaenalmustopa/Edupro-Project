/**
 * 
 */
package org.edupro.webapi.student.model;

import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "t_student")
public class StudentEntity extends BaseIdEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "nisn", length = 20)
	private String nisn;
	
	@Column(name = "pob", length = 50)
	private String pob;
	
	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "gender", length = 20)
	private String gender;

	@Column(name = "religion", length = 20)
	private String religion;

	@Column(name = "blood_type", length = 2)
	private String bloodType;

	@Column(name = "no_telp", length = 20)
	private String noTelp;

	@Column(name = "email", length = 100)
	private String email;

	public StudentEntity(String name, String nisn, String gender) {
		this.name = name;
		this.nisn = nisn;
		this.gender = gender;
	}
}
