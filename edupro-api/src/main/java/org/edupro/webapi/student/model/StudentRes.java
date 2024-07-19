package org.edupro.webapi.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRes {
    private String id;
    private String name;
    private String nisn;
    private String pob;
    private String dob;
    private String gender;
    private String religion;
    private String bloodType;
    private String noTelp;
    private String email;
    private DataStatus status;
}
