package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorType;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends Neo4jRepository<Sensor, Long> {
    @Query("MATCH p=(s)-[r:CONNECTED_TO]->(g) where ID(g) = $0 RETURN collect(s)")
    public List<Sensor> findByGatewayId(Long id);

    @Query("MATCH (s:Sensor) where $0 in s.types RETURN collect(s)")
    public List<Sensor> findByType(SensorType type);
}
