package org.edupro.webapi.courses.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseReq {
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;
    private String description;
    @NotEmpty(message = "shortName tidak boleh kosong")
    private String shortName;
    private Boolean shown;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String subjectId;
    private String levelId;
}
