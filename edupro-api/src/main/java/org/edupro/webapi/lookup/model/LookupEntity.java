package org.edupro.webapi.lookup.model;

import jakarta.persistence.*;
import lombok.*;
import org.edupro.webapi.base.model.BaseIdEntity;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_lookup")
public class LookupEntity extends BaseIdEntity {
    @Column(name = "category", length = 32, nullable = false)
    private String group;

    @Column(name = "code", length = 32, nullable = false)
    private String code;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "position", nullable = false)
    private Integer position;

    public LookupEntity(String id, String group, String code, String nama, Integer position) {
        this.setId(id);
        this.group = group;
        this.code = code;
        this.name = nama;
        this.position = position;
    }
}
