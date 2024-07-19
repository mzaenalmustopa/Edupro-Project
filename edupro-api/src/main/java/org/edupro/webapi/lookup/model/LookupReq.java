package org.edupro.webapi.lookup.model;

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
public class LookupReq {
    private String id;

    @NotEmpty
    @Size(min = 2, max = 32, message = "Kode minimal 2 dan maximal 32")
    private String group;

    @NotEmpty
    @Size(min = 2, max = 32, message = "Kode minimal 2 dan maximal 32")
    private String code;

    @NotEmpty
    @Size(min = 4, max = 128, message = "Nama minimal 4 dan maximal 128")
    private String name;

    @NotNull(message = "urutan tidak boleh kosong")
    private Integer position;
}
