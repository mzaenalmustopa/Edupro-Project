package org.edupro.webapi.praying.repository;

import org.edupro.webapi.constant.DataStatus;
import org.edupro.webapi.praying.model.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepo extends JpaRepository<QuoteEntity, String> {
    boolean existsByName(String name);
    List<QuoteEntity> findAllByStatus(DataStatus status);
}
