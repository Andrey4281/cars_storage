package ru.job4j.cars_storage.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "drivers")
    private Set<Car> cars;

    public Driver() {
    }

    public Driver(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Driver(String name) {
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Driver driver = (Driver) o;
        return id == driver.id
                && Objects.equals(name, driver.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
