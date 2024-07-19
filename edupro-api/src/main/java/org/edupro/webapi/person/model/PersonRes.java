package org.edupro.webapi.person.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRes {
    private String id;
    private String userId;
    private String nik; // NIK KTP
    private String personNo;
    private String fullName;
    private String address;
    private String dob;
    private String pob;
    private String gender;
    private String religion;
    private String bloodType;
    private String telephone;
    private String email;
    private DataStatus status;
}
