package org.edupro.webapi.curriculum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumReq {

    @NotEmpty
    @Size(min = 2, max = 20, message = "Kode minimal 2 dan maximal 20")
    private String code;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Nama minimal 4 dan maximal 50")
    private String name;


    private Integer position;
}
