package org.edupro.webapi.courses.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.student.model.StudentEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_course_student")
public class CourseStudentEntity extends BaseIdEntity {
    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "student_id", insertable = false, updatable = false)
    private String studentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    public CourseStudentEntity(CourseEntity course, StudentEntity student) {
        this.course = course;
        this.student = student;
    }
}
