package by.epam.ivanchenko.dto;

import by.epam.ivanchenko.model.Sensor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull
    @Min(value = -100, message = "Temperature value must be between -100 and 100 degrees")
    @Max(value = 100, message = "Temperature value must be between -100 and 100 degrees")
    private double value;

    @NotNull
    private boolean raining;

    private Sensor sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public boolean IsRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
