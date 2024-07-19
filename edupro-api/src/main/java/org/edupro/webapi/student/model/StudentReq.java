package org.edupro.webapi.student.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentReq {

    @NotEmpty
    @Size(min = 4, max = 100, message = "Kode minimal 4 dan maximal 100")
    private String name;

    @NotEmpty
    @Size(min = 8, max = 20, message = "Kode minimal 8 dan maximal 20")
    private String nisn;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Kode minimal 4 dan maximal 100")
    private String pob;

    @NotNull
    @Temporal(TemporalType.DATE)
    private LocalDate dob;

    private String gender;
    private String religion;
    private String bloodType;
    private String noTelp;
    private String email;
}
