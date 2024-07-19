package org.edupro.webapi.level.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelRes {
    private String id;
    private String institutionId;
    private String institutionName;
    private String code;
    private String name;
    private Integer position;
    private DataStatus status;
}
