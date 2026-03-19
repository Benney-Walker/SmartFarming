package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.ProcessedEnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedEnvironmentalDataRepo extends JpaRepository<ProcessedEnvironmentalData, Integer> {
}
