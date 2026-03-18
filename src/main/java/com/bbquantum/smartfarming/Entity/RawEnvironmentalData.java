package com.bbquantum.smartfarming.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RawEnvironmentalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rawDataId;

    @ManyToOne
    @JoinColumn(name = "Fields_fieldId")
    private Fields field;

    private String soilMoisture;

    private String temperature;

    private String humidity;

    private String rainDetection;

    private String lightIntensity;

    private LocalDateTime timeOfArrival;

    @OneToOne(mappedBy = "rawData")
    private ProcessedEnvironmentalData processedData;
}
