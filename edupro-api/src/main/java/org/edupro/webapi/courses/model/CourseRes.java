package org.edupro.webapi.courses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRes {
    private String id;
    private String name;
    private String description;
    private String shortName;
    private Boolean shown;
    private String startDate;
    private String endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String subjectId;
    private String subjectName;
    private String levelId;
    private String levelName;
    private DataStatus status;
    private List<CourseSectionRes> sections = new ArrayList<>();
}
