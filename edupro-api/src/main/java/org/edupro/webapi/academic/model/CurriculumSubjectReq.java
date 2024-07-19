package org.edupro.webapi.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumSubjectReq {
    @NotNull(message = "institutionId tidak boleh kosong")
    @Size(min = 32, max = 36, message = "institutionId minimal 32 dan maksimal 36")
    private Integer institutionId;

    @NotEmpty(message = "subjectId tidak boleh kosong")
    @Size(min = 32, max = 36, message = "subjectId minimal 32 dan maksimal 36")
    private String subjectId;

    @NotEmpty(message = "curriculumId tidak boleh kosong")
    @Size(min = 32, max = 36, message = "curriculumId minimal 32 dan maksimal 36")
    private String curriculumId;

    @NotEmpty(message = "kode tidak boleh kosong")
    @Size(min = 4, max = 20, message = "Kode minimal 4 dan maksimal 20")
    private String code;
}
