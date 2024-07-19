package org.edupro.webapi.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePersonReq {
    private String courseId;
    private String personId;
}
