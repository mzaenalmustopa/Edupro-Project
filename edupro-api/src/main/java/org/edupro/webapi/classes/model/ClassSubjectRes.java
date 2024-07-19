package org.edupro.webapi.classes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassSubjectRes {
    private String id;
    private String kelasId;
    private String mapelId;
    private double nilaiKKM;
}
