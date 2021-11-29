package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import com.energybox.backendcodingchallenge.dto.SensorRequest;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value = "/sensors" )
public class SensorController {

    @Autowired
    SensorService sensorService;

    @ApiOperation( value = "create a sensor", response = Sensor.class )
    @RequestMapping( value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Sensor> create(
            @RequestBody SensorRequest request
    ) {
        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(request, sensor);
        Sensor response = sensorService.createUpdateSensor(sensor);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation( value = "update a sensor", response = Sensor.class )
    @RequestMapping( value = "/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Sensor> update(
            @PathVariable Long id,
            @RequestBody SensorRequest request
    ) {
        Sensor sensor = sensorService.getSensorById(id);
        if (sensor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            BeanUtils.copyProperties(request, sensor);
            Sensor response = sensorService.createUpdateSensor(sensor);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @ApiOperation( value = "get a sensor", response = Sensor.class )
    @RequestMapping( value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Sensor> get(
            @PathVariable Long id
    ) {
        Sensor response = sensorService.getSensorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation( value = "get all sensors", response = List.class )
    @RequestMapping( value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Sensor>> getAll(
            @RequestParam(required = false) SensorType type
    ) {
        if (type != null) {
            List<Sensor> response = sensorService.getSensorsByType(type);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            List<Sensor> response = sensorService.getSensors();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}
