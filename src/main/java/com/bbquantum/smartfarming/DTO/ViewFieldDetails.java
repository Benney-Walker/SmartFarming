package com.bbquantum.smartfarming.DTO;

public class ViewFieldDetails {

    private String fieldName;

    private String location;

    private String size;

    private String status;

    public ViewFieldDetails(String fieldName, String location, String size, String status) {
        this.fieldName = fieldName;
        this.location = location;
        this.size = size;
        this.status = status;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLocation() {
        return location;
    }

    public String getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }
}
