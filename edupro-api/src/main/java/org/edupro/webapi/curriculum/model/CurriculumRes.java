package org.edupro.webapi.curriculum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurriculumRes {
    private String id;
    private String code;
    private String name;
    private Integer position;
    private DataStatus status;
}
