package by.epam.ivanchenko.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull
    @Min(value = -100, message = "Temperature value must be between -100 and 100 degrees")
    @Max(value = 100, message = "Temperature value must be between -100 and 100 degrees")
    private double value;

    @Column(name = "raining")
    //@NotEmpty(message = "Raining status can't be empty!")
    @NotNull
    private boolean raining;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)                               // or date or time. CHECK
    private LocalDateTime createdAt;

    // @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

//    public Measurement(double value, boolean raining) {
//        this.value = value;
//        this.raining = raining;
//    }

    public Measurement() {

    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                ", sensor=" + sensor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;

        if (id != that.id) return false;
        if (Double.compare(that.value, value) != 0) return false;
        if (raining != that.raining) return false;
        return sensor != null ? sensor.equals(that.sensor) : that.sensor == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (raining ? 1 : 0);
        result = 31 * result + (sensor != null ? sensor.hashCode() : 0);
        return result;
    }


}
