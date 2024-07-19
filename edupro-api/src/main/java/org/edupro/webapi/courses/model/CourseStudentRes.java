package org.edupro.webapi.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentRes {
    private String courseId;
    private String siswaId;
    private String siswaName;
}
