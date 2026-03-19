package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.Decisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionsRepo extends JpaRepository<Decisions, Integer> {
}
