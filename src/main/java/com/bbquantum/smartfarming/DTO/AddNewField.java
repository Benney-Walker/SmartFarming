package com.bbquantum.smartfarming.DTO;

public class AddNewField {

    private String fieldName;

    private String fieldLocation;

    private String userName;

    public AddNewField(String fieldName, String fieldLocation, String userName) {
        this.fieldName = fieldName;
        this.fieldLocation = fieldLocation;
        this.userName = userName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public String getUserName() {
        return userName;
    }
}
