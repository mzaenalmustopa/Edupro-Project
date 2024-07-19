package org.edupro.webapi.building.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingRes {
    private String id;
    private String code;
    private String name;
    private DataStatus status;
}
