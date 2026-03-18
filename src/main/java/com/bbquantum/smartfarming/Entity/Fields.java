package com.bbquantum.smartfarming.Entity;

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

    public Fields() {}
}
