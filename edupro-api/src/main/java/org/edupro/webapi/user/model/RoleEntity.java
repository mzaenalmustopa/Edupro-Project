package org.edupro.webapi.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_role")
public class RoleEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 64)
    private String name;

    public RoleEntity(String name) {
        this.name = name;
    }
}
