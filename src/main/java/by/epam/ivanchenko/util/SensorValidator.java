package by.epam.ivanchenko.util;

import by.epam.ivanchenko.model.Sensor;
import by.epam.ivanchenko.service.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorService.findSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with such name is already exists!");
        }
    }
}
