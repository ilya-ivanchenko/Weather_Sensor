package by.epam.ivanchenko.service;

import by.epam.ivanchenko.model.Sensor;
import by.epam.ivanchenko.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void save(Sensor sensor) {
        // TO DO: DTO + Valid on existing name sensor
        sensorRepository.save(sensor);
    }
}
