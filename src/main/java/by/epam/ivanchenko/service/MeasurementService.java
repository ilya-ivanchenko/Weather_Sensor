package by.epam.ivanchenko.service;

import by.epam.ivanchenko.model.Measurement;
import by.epam.ivanchenko.repository.MeasurementRepository;
import by.epam.ivanchenko.repository.SensorRepository;
import by.epam.ivanchenko.util.MeasurementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
            enrichMeasurement(measurement);
            measurementRepository.save(measurement);
    }

    public long getRainyDays() {
        return measurementRepository.findAll().stream().filter(Measurement::getRaining).count();
        // SELECT  COUNT(*)  FROM measurement WHERE raining =1;
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorRepository.findSensorByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
