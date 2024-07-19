package org.edupro.webapi.classes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.constant.DataStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRes {
    private String id;
    private String code;
    private String name;

    private String roomId;
    private String roomCode;

    private String institutionId;
    private String institutionName;

    private String academicYearId;
    private String academicYearName;

    private String levelId;
    private String levelName;

    private String academicSessionId;
    private Integer semester;

    private String homeroomTeacherId;
    private String homeroomTeacherName;

    private DataStatus status;
}
