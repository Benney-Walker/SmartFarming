package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.DTO.AddNewField;
import com.bbquantum.smartfarming.DTO.ViewFieldDetails;
import com.bbquantum.smartfarming.Entity.Fields;
import com.bbquantum.smartfarming.Entity.Users;
import com.bbquantum.smartfarming.Repository.FieldsRepo;
import com.bbquantum.smartfarming.Repository.UsersRepo;
import com.bbquantum.smartfarming.Utility.IdGenerationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {

    private final FieldsRepo fieldsRepo;

    private final UsersRepo usersRepo;

    private final IdGenerationUtil idGenerationUtil;

    public FieldService(FieldsRepo fieldsRepo, UsersRepo usersRepo, IdGenerationUtil idGenerationUtil) {
        this.fieldsRepo = fieldsRepo;
        this.usersRepo = usersRepo;
        this.idGenerationUtil = idGenerationUtil;
    }

    public ResponseEntity<?> addNewField(AddNewField addNewField) {
        String fieldName = addNewField.getFieldName();
        String fieldLocation = addNewField.getFieldLocation();
        String userId = addNewField.getUserId();

        Users user = usersRepo.findByUserId(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "Selected User not found."
            ));
        }

        if (fieldsRepo.findByFieldName(fieldName).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "message", "Field already exists."
            ));
        }

        Fields field = new Fields();
        field.setFieldId(idGenerationUtil.generateEntityId("FIELD"));
        field.setFieldName(fieldName);
        field.setFieldLocation(fieldLocation);
        field.setDateOfRegistration(LocalDate.now());
        field.setUser(user);
        fieldsRepo.save(field);

        List<Fields> userFields = user.getFields();
        if (userFields == null) {
            userFields = new ArrayList<>();
        }
        userFields.add(field);
        usersRepo.save(user);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> loadAllFields() {
        List<Fields> fields = fieldsRepo.findAll();
        if (fields.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message", "No fields found"
            ));
        }

        return ResponseEntity.ok(fields.stream().map(field -> {
            String farmName = field.getFieldName();
            String fieldLocation = field.getFieldLocation();
            String fieldSize = field.getFieldSize();
            String fieldStatus = field.getFieldStatus().name();

            return new ViewFieldDetails(
                    farmName,
                    fieldLocation,
                    fieldSize,
                    fieldStatus
            );
        }).toList());
    }
}
