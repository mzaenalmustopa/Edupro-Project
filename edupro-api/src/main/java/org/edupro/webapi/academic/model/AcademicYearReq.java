package org.edupro.webapi.academic.model;

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
public class AcademicYearReq {

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Size(min = 2, max = 20, message = "Nama tidak boleh kosong")
    private String name;

    @NotEmpty(message = "Nama tidak boleh kosong")
    @Size(min = 32, max = 36, message = "Nama tidak boleh kosong")
    private String curriculumId;

    @NotNull(message = "startDate tidak bolek kosong")
    private LocalDate startDate;

    @NotNull(message = "endDate tidak boleh kosong")
    private LocalDate endDate;
}
