package by.epam.ivanchenko.controller;

import by.epam.ivanchenko.model.Measurement;
import by.epam.ivanchenko.service.MeasurementService;
import by.epam.ivanchenko.util.MeasurementAddingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    public List<Measurement> getMeasurements() {
        return measurementService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid Measurement measurement, BindingResult bindingResult) {                       // add DTO
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementAddingException(errorMessage.toString());
        }

        measurementService.save(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDays() {
      return  measurementService.getRainyDays();
    }



    // TO DO @ExceptionHandler   + http status
}
