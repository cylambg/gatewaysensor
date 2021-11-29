package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatewayRepository extends Neo4jRepository<Gateway, Long> {
    @Query("MATCH p=(s)-[r:CONNECTED_TO]->(g) where $0 in s.types RETURN collect(g)")
    public List<Gateway> findBySensorType(SensorType type);
}
