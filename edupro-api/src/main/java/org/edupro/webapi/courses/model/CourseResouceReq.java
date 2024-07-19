package org.edupro.webapi.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResouceReq {
    private String id;
    private String courseId;
    private String sectionType;
    private String name;
    private String description;
    private String parentId;
    private Integer noUrut;
}
