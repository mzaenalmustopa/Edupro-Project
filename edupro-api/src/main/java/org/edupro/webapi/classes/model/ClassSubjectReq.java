package org.edupro.webapi.classes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubjectReq {
    private String id;
    @NotEmpty
    @Size(min = 32, max = 36, message = "kelasId minimal 32 dan maximal 36")
    private String kelasId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "mapelId minimal 32 dan maximal 36")
    private String mapelId;
    private double nilaiKKM;
}
