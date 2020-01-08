package ru.job4j.cars_storage.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="carcass")
public class Carcass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "carcass")
    private List<Car> cars;

    public Carcass() {
    }

    public Carcass(int id, String name, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.cars = cars;
    }

    public Carcass(String name) {
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
        Carcass carcass = (Carcass) o;
        return id == carcass.id
                && Objects.equals(name, carcass.name);
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
        return "Carcass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
