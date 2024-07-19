package org.edupro.webapi.building.model;

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
public class RoomReq {
    private String id;
    @NotEmpty
    @Size(min = 4, max = 20, message = "Kode minimal 4 dan maximal 20")
    private String code;

    @NotEmpty
    @Size(min = 4, max = 50, message = "Kode minimal 4 dan maximal 50")
    private String name;

    @NotNull(message = "kapasitas tidak boleh")
    private Integer capacity;

    @NotEmpty
    @Size(min = 32, max = 36, message = "gedungId minimal 32 dan maximal 36")
    private String buildingId;
}
