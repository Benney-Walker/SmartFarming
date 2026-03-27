package com.bbquantum.smartfarming.Utility;

import com.bbquantum.smartfarming.Entity.IdGenerationEntity;
import com.bbquantum.smartfarming.Repository.IdGenerationRepo;
import org.springframework.stereotype.Component;

@Component
public class IdGenerationUtil {

    private final IdGenerationRepo idGenerationRepo;

    public IdGenerationUtil(IdGenerationRepo idGenerationRepo) {
        this.idGenerationRepo = idGenerationRepo;
    }

    public String generateEntityId(String entityName) {
        String entityId = "";

        switch (entityName) {
            case "FIELD":
                String prefix = "FD";
                long idCode = 100700;
                entityId = generateEntityId(entityName, prefix, idCode);
                break;

            case "USER":
                break;
        }

        return entityId;
    }

    private String generateEntityId(String entityName, String prefix, long idCode) {
        try {
            IdGenerationEntity entity = idGenerationRepo.findByEntityName(entityName)
                    .orElse(new IdGenerationEntity(entityName, idCode));

            String newEntityId = prefix + entity.getEntityCode();
            entity.setEntityCode(entity.getEntityCode() + 1);
            idGenerationRepo.save(entity);

            return newEntityId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
