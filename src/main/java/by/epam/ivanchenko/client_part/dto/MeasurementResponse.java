package by.epam.ivanchenko.client_part.dto;

import by.epam.ivanchenko.dto.MeasurementDTO;

import java.util.List;

public class MeasurementResponse {
    private List<by.epam.ivanchenko.dto.MeasurementDTO> measurements;

    public List<by.epam.ivanchenko.dto.MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
