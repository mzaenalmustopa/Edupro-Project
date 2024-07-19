package org.edupro.webapi.person.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonReq {
    private String userId;
    private String nik; // NIK KTP
    private String personNo;
    private String fullName;
    private String address;
    @Temporal(TemporalType.DATE)
    private LocalDate dob;
    private String pob;
    private String gender;
    private String religion;
    private String bloodType;
    private String telephone;
    private String email;
}
