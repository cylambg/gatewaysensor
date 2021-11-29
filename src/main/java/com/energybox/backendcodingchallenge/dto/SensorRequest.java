package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.SensorLastReading;
import com.energybox.backendcodingchallenge.domain.SensorType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SensorRequest {
    private String name;
    private List<SensorType> types;
//    private Map<SensorType, SensorLastReading> last_reading;
}
