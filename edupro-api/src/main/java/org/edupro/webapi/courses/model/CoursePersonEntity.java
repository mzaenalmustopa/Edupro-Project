package org.edupro.webapi.courses.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;
import org.edupro.webapi.person.model.PersonEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_course_person")
public class CoursePersonEntity extends BaseIdEntity {
    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(name = "person_id", insertable = false, updatable = false)
    private String personId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    public CoursePersonEntity(CourseEntity course, PersonEntity person) {
        this.course = course;
        this.person = person;
    }
}
