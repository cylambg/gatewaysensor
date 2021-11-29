package com.energybox.backendcodingchallenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.NonNull;
import org.neo4j.driver.internal.shaded.reactor.util.annotation.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Map;

@Node
@Data
public class Sensor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private List<SensorType> types;
//    private Map<SensorType, SensorLastReading> last_reading;

    @Relationship(type = "CONNECTED_TO")
    private Gateway gateway;
}
