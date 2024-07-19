package org.edupro.webapi.institution.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionRes {
    private String id;
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Size(max = 50)
    private String shortName;

    @Size(max = 100)
    private String regNumber;

    @Size(max = 100)
    private String code;
    private String expiredDate;

    @Size(max = 20)
    private String levelCategory;

    @Size(max = 100)
    private String headmaster;

    @Size(max = 100)
    private String uniqueNumber;

    @Size(max = 100)
    private String adminName;

    private int maxExamUser;

    private int maxLmsUser;

    private int diffServerTime;

    private int effectiveDays;

    @Size(max = 5)
    private String startedDay;
    @Size(max = 5)
    private String endDay;
    @Size(max = 5)
    private String endEarly;
    @Size(max = 5)
    private String endOfDay;

    @Size(max = 20)
    private String provinceId;
    @Size(max = 20)
    private String cityId;
    @Size(max = 20)
    private String districtId;
    @Size(max = 20)
    private String subDistrictId;


    @Size(max = 255)
    private String address;
    @Size(max = 6)
    private String postalCode;
    @Size(max = 50)
    private String phoneNumber;
    @Size(max = 20)
    private String faxNumber;
    @Size(max= 100)
    private String website;
    @Size(max= 100)
    private String email;

    private String letterHead;
    private String headOfSignature;
    private String serviceLogo;
    private String institutionLogo;
    private String stamp;
    private DataStatus status;
}
