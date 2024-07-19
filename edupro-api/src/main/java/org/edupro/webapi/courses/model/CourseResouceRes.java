package org.edupro.webapi.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResouceRes {
    private String id;
    private String courseId;
    private String courseSectionId;
    private Integer type;
    private String name;
    private String attachmentId;
}
