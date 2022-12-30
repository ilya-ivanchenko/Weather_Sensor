package by.epam.ivanchenko.repository;

import by.epam.ivanchenko.model.Measurement;
import by.epam.ivanchenko.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
