package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.SystemActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemActivityLogRepo extends JpaRepository<SystemActivityLog, Integer> {

}
