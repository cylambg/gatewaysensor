package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.dto.GatewayRequest;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.service.GatewayService;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping( value = "/gateways" )
public class GatewayController {

    @Autowired
    GatewayService gatewayService;

    @Autowired
    SensorService sensorService;

    @ApiOperation( value = "create a gateway", response = Gateway.class )
    @RequestMapping( value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Gateway> create(
            @RequestBody GatewayRequest request
    ) {
        Gateway gateway = new Gateway();
        BeanUtils.copyProperties(request, gateway);
        Gateway response = gatewayService.createUpdateGateway(gateway);
        return ResponseEntity.ok(response);
    }

    @ApiOperation( value = "update a gateway", response = Gateway.class )
    @RequestMapping( value = "/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Gateway> update(
            @PathVariable Long id,
            @RequestBody GatewayRequest request
    ) {
        Gateway gateway = gatewayService.getGatewayById(id);
        if (gateway == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            BeanUtils.copyProperties(request, gateway);
            Gateway response = gatewayService.createUpdateGateway(gateway);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @ApiOperation( value = "get a gateway", response = Gateway.class )
    @RequestMapping( value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Gateway> get(
            @PathVariable Long id
    ) {
        Gateway response = gatewayService.getGatewayById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation( value = "get all gateways", response = List.class )
    @RequestMapping( value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Gateway>> getAll(
            @RequestParam(required = false) SensorType type
    ) {
        if (type != null) {
            List<Gateway> response = gatewayService.getGatewaysBySensorType(type);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            List<Gateway> response = gatewayService.getGateways();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @ApiOperation( value = "assign a sensor to a given gateway", response = Sensor.class )
    @RequestMapping( value = "/{gatewayId}/sensors/{sensorId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Sensor> assignSensorToGateway(
            @PathVariable Long gatewayId,
            @PathVariable Long sensorId
    ) {
        Gateway gateway = gatewayService.getGatewayById(gatewayId);
        Sensor sensor = sensorService.getSensorById(sensorId);
        if (gateway == null || sensor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            sensor.setGateway(gateway);
            Sensor response = sensorService.createUpdateSensor(sensor);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @ApiOperation( value = "get sensors assigned to a gateway", response = List.class )
    @RequestMapping( value = "/{gatewayId}/sensors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Sensor>> getSensorsByGateway(
            @PathVariable Long gatewayId
    ) {
        Gateway gateway = gatewayService.getGatewayById(gatewayId);
        if (gateway == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<Sensor> response = sensorService.getSensorsByGatewayId(gatewayId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
