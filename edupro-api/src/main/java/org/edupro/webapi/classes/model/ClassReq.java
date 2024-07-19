package org.edupro.webapi.classes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassReq {
    private String code;
    private String name;

    @NotEmpty
    @Size(min = 32, max = 36, message = "ruangId minimal 32 dan maximal 36")
    private String roomId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "lembagaId minimal 32 dan maximal 36")
    private String institutionId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "tahunAjaranId minimal 32 dan maximal 36")
    private String academicYearId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "levelId minimal 32 dan maximal 36")
    private String levelId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "SesiAkademikId minimal 32 dan maximal 36")
    private String academicSessionId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "waliKelasId minimal 32 dan maximal 36")
    private String homeroomTeacherId;
}
