package ru.job4j.cars_storage.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "engine")
    private List<Car> cars;

    public Engine() {
    }

    public Engine(int id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public Engine(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Engine engine = (Engine) o;
        return id == engine.id
                && Objects.equals(name, engine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
