/**
 * 
 */
package org.edupro.webapi.institution.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

import java.time.LocalDate;

/**
 * @author Awiyanto Ajisasongko
 *
 * Oct 7, 2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_institution")
public class InstitutionEntity extends BaseIdEntity {
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "short_name", length = 50)
	private String shortName;
	
	/**
	 * NIS/NSS/NDS
	 */
	@Column(name = "reg_number", length = 100)
	private String regNumber;
	
	/**
	 * Kode Sekolah / NPSN
	 */
	@Column(name = "code", length = 100)
	private String code;
	
	@Column(name = "expired_date")
	private LocalDate expiredDate;
	
	@Column(name = "level_category", length = 20)
	private String levelCategory;
	
	@Column(name = "head_master", length = 100)
	private String headmaster;

	@Column(name = "unique_number", length = 100)
	private String uniqueNumber;

	@Column(name = "admin_name", length = 100)
	private String adminName;

	@Column(name = "max_exam_user")
	private int maxExamUser;

	@Column(name = "max_lms_user")
	private int maxLmsUser;

	@Column(name = "diff_server_time")
	private int diffServerTime;

	@Column(name = "effective_days")
	private int effectiveDays;

	@Column(name = "started_day", length = 5)
	private String startedDay;

	@Column(name = "end_day", length = 5)
	private String endDay;

	@Column(name = "end_early", length = 5)
	private String endEarly;

	@Column(name = "endOfDay", length = 5)
	private String endOfDay;

	@Column(name = "province_id", length = 20)
	private String provinceId;

	@Column(name = "city_id", length = 20)
	private String cityId;

	@Column(name = "district_id", length = 20)
	private String districtId;

	@Column(name = "sub_district_id", length = 20)
	private String subDistrictId;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "postal_code", length = 6)
	private String postalCode;

	@Column(name = "phone_number", length = 50)
	private String phoneNumber;

	@Column(name = "fax_number", length = 20)
	private String faxNumber;

	@Column(name = "website", length = 100)
	private String website;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "letter_head", length = 36)
	private String letterHead;

	@Column(name = "head_of_signature", length = 36)
	private String headOfSignature;

	@Column(name = "service_logo", length = 36)
	private String serviceLogo;

	@Column(name = "institution_logo", length = 36)
	private String institutionLogo;

	@Column(name = "stamp", length = 36)
	private String stamp;
}
