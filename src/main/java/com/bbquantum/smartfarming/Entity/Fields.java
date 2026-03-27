package com.bbquantum.smartfarming.Entity;

import com.bbquantum.smartfarming.Constants.FieldStatus;
import com.bbquantum.smartfarming.Constants.IrrigationDecision;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Fields {

    @Id
    private String fieldId;

    @Column(nullable = false)
    private String fieldName;

    @Column(nullable = false)
    private String fieldLocation;

    @Column(nullable = false)
    private LocalDate dateOfRegistration;

    @ManyToOne
    @JoinColumn(name = "User_field", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "field")
    private List<RawEnvironmentalData> rawEnvironmentalData;

    @OneToMany(mappedBy = "field")
    private List<Decisions> decisions;

    @Enumerated(EnumType.STRING)
    private FieldStatus fieldStatus;

    public Fields() {}

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldLocation() {
        return fieldLocation;
    }

    public void setFieldLocation(String fieldLocation) {
        this.fieldLocation = fieldLocation;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<RawEnvironmentalData> getRawEnvironmentalData() {
        return rawEnvironmentalData;
    }

    public void setRawEnvironmentalData(List<RawEnvironmentalData> rawEnvironmentalData) {
        this.rawEnvironmentalData = rawEnvironmentalData;
    }

    public List<Decisions> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<Decisions> decisions) {
        this.decisions = decisions;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(FieldStatus fieldStatus) {
        this.fieldStatus = fieldStatus;
    }
}
