package org.edupro.webapi.user.repository;

import org.edupro.webapi.user.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(String name);
    List<RoleEntity> findByNameIn(List<String> roles);
}
