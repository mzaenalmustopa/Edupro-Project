package org.edupro.webapi.lookup.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupRes {
    private String id;
    private String group;
    private String code;
    private String name;
    private Integer position;
    private DataStatus status;

    public LookupRes(String group, String code, String name, Integer position) {
        this.group = group;
        this.code = code;
        this.name = name;
        this.position = position;
    }
}
