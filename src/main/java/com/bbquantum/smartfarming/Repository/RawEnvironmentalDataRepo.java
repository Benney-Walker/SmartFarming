package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.RawEnvironmentalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawEnvironmentalDataRepo extends JpaRepository<RawEnvironmentalData, Integer> {
}
