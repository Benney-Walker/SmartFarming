package com.bbquantum.smartfarming.DTO;

public class AddNewField {

    private String fieldName;

    private String fieldLocation;

    private String userId;

    public AddNewField(String fieldName, String fieldLocation, String userId) {
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.userId = userId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public String getUserId() {
        return userId;
    }
}
