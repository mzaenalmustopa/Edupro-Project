package org.edupro.webapi.classes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassStudentReq {
    private String id;
    @NotEmpty
    @Size(min = 32, max = 36, message = "sesiAkademikId minimal 32 dan maximal 36")
    private String sesiAkademikId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "kelasId minimal 32 dan maximal 36")
    private String kelasId;

    @NotEmpty
    @Size(min = 32, max = 36, message = "siswaId minimal 32 dan maximal 36")
    private String siswaId;
    private double nilaiTugas;
    private double nilaiUTS;
    private double nilaiUAS;
    private double nilaiAkhir;
    private String catatan;
}
