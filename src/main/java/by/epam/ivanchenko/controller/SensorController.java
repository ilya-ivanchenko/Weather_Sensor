package by.epam.ivanchenko.controller;

import by.epam.ivanchenko.model.Sensor;
import by.epam.ivanchenko.service.SensorService;
import by.epam.ivanchenko.util.SensorRegistrationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public List<Sensor> getSensors() {                                                                                               // add DTO
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid Sensor sensor, BindingResult bindingResult) {                   // add DTO

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorRegistrationException(errorMessage.toString());
        }

        sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    // TO DO @ExceptionHandler   + http status

}
