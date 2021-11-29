package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayService {

    @Autowired
    GatewayRepository gatewayRepository;

    public Gateway getGatewayById(Long id) {
        return gatewayRepository.findById(id).orElse(null);
    }

    public List<Gateway> getGateways() {
        return gatewayRepository.findAll();
    }

    public Gateway createUpdateGateway(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    public List<Gateway> getGatewaysBySensorType(SensorType type) {
        return gatewayRepository.findBySensorType(type);
    }
}
