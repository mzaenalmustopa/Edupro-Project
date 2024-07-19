/**
 * 
 */
package org.edupro.webapi.person.model;

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
@Table(name = "t_person")
public class PersonEntity extends BaseIdEntity {
	@Column(name = "user_id", length = 100)
	private String userId;

	@Column(name = "nik", length = 50)
	private String nik; // NIK KTP

	@Column(name = "person_no", length = 50, nullable = false)
	private String personNo;
	
	@Column(name = "name", length = 100, nullable = false)
	private String fullName;
	
	@Column(name = "address", length = 255)
	private String address;
	
	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "pob", length = 100)
	private String pob;

	@Column(name = "gender", length = 20)
	private String gender;

	@Column(name = "religion", length = 20)
	private String religion;

	@Column(name = "blood_type", length = 2)
	private String bloodType;

	@Column(name = "telephone", length = 20)
	private String telephone;

	@Column(name = "email", length = 100)
	private String email;

	public PersonEntity(String personNo, String fullName, String nik) {
		this.personNo = personNo;
		this.fullName = fullName;
		this.nik = nik;
	}
}
