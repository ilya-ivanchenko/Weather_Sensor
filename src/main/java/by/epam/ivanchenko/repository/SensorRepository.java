package by.epam.ivanchenko.repository;

import by.epam.ivanchenko.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findSensorByName(String sensorName);
}
