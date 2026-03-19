package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.IrrigationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationActionRepo extends JpaRepository<IrrigationAction, Integer> {
}
