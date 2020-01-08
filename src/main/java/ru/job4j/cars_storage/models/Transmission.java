package ru.job4j.cars_storage.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transmission")
    private List<Car> cars;

    public Transmission() {
    }

    public Transmission(int id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public Transmission(String name) {
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
        Transmission that = (Transmission) o;
        return id == that.id
                && Objects.equals(name, that.name);
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
        return "Transmission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
