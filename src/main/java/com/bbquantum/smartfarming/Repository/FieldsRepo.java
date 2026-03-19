package com.bbquantum.smartfarming.Repository;

import com.bbquantum.smartfarming.Entity.Fields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldsRepo extends JpaRepository<Fields, Integer> {

}
