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

    public ProcessedEnvironmentalData() {}

    public long getProcessedDataId() {
        return processedDataId;
    }

    public RawEnvironmentalData getRawData() {
        return rawData;
    }

    public void setRawData(RawEnvironmentalData rawData) {
        this.rawData = rawData;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(String soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRainDetection() {
        return rainDetection;
    }

    public void setRainDetection(String rainDetection) {
        this.rainDetection = rainDetection;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalDateTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public LocalDateTime getTimeAfterProcessing() {
        return timeAfterProcessing;
    }

    public void setTimeAfterProcessing(LocalDateTime timeAfterProcessing) {
        this.timeAfterProcessing = timeAfterProcessing;
    }
}
