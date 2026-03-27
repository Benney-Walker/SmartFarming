package com.bbquantum.smartfarming.Service;

import com.bbquantum.smartfarming.DTO.RawSensorsData;
import com.bbquantum.smartfarming.Entity.Fields;
import com.bbquantum.smartfarming.Entity.RawEnvironmentalData;
import com.bbquantum.smartfarming.Repository.FieldsRepo;
import com.bbquantum.smartfarming.Repository.ProcessedEnvironmentalDataRepo;
import com.bbquantum.smartfarming.Repository.RawEnvironmentalDataRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnvironmentalDataService {

    private final ProcessedEnvironmentalDataRepo processedData;

    private final RawEnvironmentalDataRepo rawDataRepo;

    private final FieldsRepo fieldsRepo;

    public EnvironmentalDataService(ProcessedEnvironmentalDataRepo processedData, RawEnvironmentalDataRepo rawDataRepo,
                                    FieldsRepo fieldsRepo) {
        this.processedData = processedData;
        this.rawDataRepo = rawDataRepo;
        this.fieldsRepo = fieldsRepo;
    }

    public ResponseEntity<?> saveNewSensorData(String fieldId, RawSensorsData data) {
        String soilMoisture = data.getSoilMoisture() == null ? "not available" : data.getSoilMoisture();
        String temperature = data.getTemperature() == null ? "not available" : data.getTemperature();
        String humidity = data.getHumidity() == null ? "not available" : data.getHumidity();
        String rainDetection = data.getRainDetection() == null ? "not available" : data.getRainDetection();
        String lightIntensity = data.getLightIntensity() == null ? "not available" : data.getLightIntensity();

        Fields field = fieldsRepo.findByFieldId(fieldId).orElse(null);
        if (field == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        RawEnvironmentalData rawData = new RawEnvironmentalData();
        rawData.setField(field);
        rawData.setSoilMoisture(soilMoisture);
        rawData.setTemperature(temperature);
        rawData.setHumidity(humidity);
        rawData.setRainDetection(rainDetection);
        rawData.setLightIntensity(lightIntensity);
        rawData.setTimeOfArrival(LocalDateTime.now());
        rawDataRepo.save(rawData);

        addToFieldDataList(field, rawData);
        return ResponseEntity.ok().build();
    }

    private void addToFieldDataList(Fields field, RawEnvironmentalData data) {
        try {
            List<RawEnvironmentalData> dataList = field.getRawEnvironmentalData();
            if (dataList == null || dataList.isEmpty()) {
                new ArrayList<>();
            }
            dataList.add(data);
            field.setRawEnvironmentalData(dataList);
            fieldsRepo.save(field);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
