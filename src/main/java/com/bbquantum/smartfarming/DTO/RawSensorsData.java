package com.bbquantum.smartfarming.DTO;

public class RawSensorsData {

    private String soilMoisture;

    private String temperature;

    private String humidity;

    private String rainDetection;

    private String lightIntensity;

    public RawSensorsData(String soilMoisture, String temperature, String humidity, String rainDetection, String lightIntensity) {
        this.soilMoisture = soilMoisture;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rainDetection = rainDetection;
        this.lightIntensity = lightIntensity;
    }

    public String getSoilMoisture() {
        return soilMoisture;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getRainDetection() {
        return rainDetection;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }
}
