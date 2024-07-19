package org.edupro.webapi.subject.repository;

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
public class SubjectReq {
    private String id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Kode minimal 2 dan maximal 10")
    private String code;

    @NotEmpty
    @Size(min = 4, max = 100, message = "Nama minimal 4 dan maximal 100")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 36, message = "Type minimal 2 dan maximal 36")
    private String types;
}
