package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.IdGenerationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdGenerationRepo extends JpaRepository<IdGenerationEntity, Long> {
    Optional<IdGenerationEntity> findByEntityName(String entityName);
}
