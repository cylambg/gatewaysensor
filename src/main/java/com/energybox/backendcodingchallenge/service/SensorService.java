package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    GatewayService gatewayService;

    public Sensor getSensorById(Long id) {
        return sensorRepository.findById(id).orElse(null);
    }

    public List<Sensor> getSensors() {
        return sensorRepository.findAll();
    }

    public List<Sensor> getSensorsByGatewayId(Long id) {
        return sensorRepository.findByGatewayId(id);
    }

    public Sensor createUpdateSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Sensor assignSensorToGateway(Long sensorId, Long gatewayId) {
        Sensor sensor = getSensorById(sensorId);
        Gateway gateway = gatewayService.getGatewayById(gatewayId);
        if (sensor != null && gateway != null) {
            sensor.setGateway(gateway);
            sensorRepository.save(sensor);
        }
        return sensor;
    }

    public List<Sensor> getSensorsByType(SensorType type) {
        return sensorRepository.findByType(type);
    }
}
