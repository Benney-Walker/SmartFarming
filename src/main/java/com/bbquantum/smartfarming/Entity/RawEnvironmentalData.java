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

    public RawEnvironmentalData() {}

    public long getRawDataId() {
        return rawDataId;
    }

    public Fields getField() {
        return field;
    }

    public void setField(Fields field) {
        this.field = field;
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

    public ProcessedEnvironmentalData getProcessedData() {
        return processedData;
    }

    public void setProcessedData(ProcessedEnvironmentalData processedData) {
        this.processedData = processedData;
    }
}
