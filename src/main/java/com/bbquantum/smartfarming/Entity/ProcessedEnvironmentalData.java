package com.bbquantum.smartfarming.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProcessedEnvironmentalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long processedDataId;

    @OneToOne(mappedBy = "processedData")
    private RawEnvironmentalData rawData;

    private String soilMoisture;

    private String temperature;

    private String humidity;

    private String rainDetection;

    private String lightIntensity;

    private LocalDateTime timeOfArrival;

    private LocalDateTime timeAfterProcessing;
}
