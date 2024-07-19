package org.edupro.webapi.level.model;

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
public class LevelReq {

    @NotEmpty
    @Size(min = 32, max = 36, message = "Nama minimal 4 dan maximal 50")
    private String institutionId;

    @NotEmpty
    @Size(min = 4, max = 20, message = "Nama minimal 4 dan maximal 50")
    private String code;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Nama minimal 4 dan maximal 50")
    private String name;
    private Integer position;
}
