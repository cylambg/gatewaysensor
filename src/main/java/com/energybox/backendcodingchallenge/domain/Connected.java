package com.energybox.backendcodingchallenge.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

@RelationshipProperties
@Data
public class Connected {
    @Id
    @GeneratedValue
    private Long id;

    private List<Sensor> sensors;

    @TargetNode
    private Gateway gateway;
}
