package org.edupro.webapi.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicSessionReq {
    private String id;

    @NotNull(message = "academicYearId tidak boleh kosong")
    @Size(min = 32, max = 36, message = "academicYearId minimal 36 dan maksimal 36")
    private String academicYearId;

    @NotNull(message = "Semester wajid diisi")
    private Integer semester;

    @NotNull(message = "startDate tidak bolek kosong")
    private LocalDate startDate;

    @NotNull(message = "endDate tidak boleh kosong")
    private LocalDate endDate;
}
