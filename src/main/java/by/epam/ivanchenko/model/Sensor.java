package by.epam.ivanchenko.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

//    @OneToMany(mappedBy = "sensor")
//    private List<Measurement> measurements;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Sensor name can't be empty!")
    @Size(min = 3, max = 30, message = "Name length must be between 3 and 30 characters")
    private String name;

    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (id != sensor.id) return false;
        return name != null ? name.equals(sensor.name) : sensor.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
