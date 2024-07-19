package org.edupro.webapi.subject.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edupro.webapi.constant.DataStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRes {
    private String id;
    private String code;
    private String name;
    private String types;
    private DataStatus status;
}
