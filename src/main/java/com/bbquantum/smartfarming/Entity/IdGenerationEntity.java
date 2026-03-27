package com.bbquantum.smartfarming.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class IdGenerationEntity {

    @Id
    private String entityName;

    private long entityCode;

    public IdGenerationEntity() {}

    public IdGenerationEntity(String entityName, long entityCode) {
        this.entityName = entityName;
        this.entityCode = entityCode;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public long getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(long entityCode) {
        this.entityCode = entityCode;
    }
}
