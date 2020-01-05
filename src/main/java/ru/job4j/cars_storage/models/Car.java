package ru.job4j.cars_storage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class Car {
    private int id;
    private String name;
    private Carcass carcass;
    private Engine engine;
    private  Transmission transmission;

    public Car() {
    }

    public Car(int id, String name, Carcass carcass, Engine engine, Transmission transmission) {
        this.id = id;
        this.name = name;
        this.carcass = carcass;
        this.engine = engine;
        this.transmission = transmission;
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

    public Carcass getCarcass() {
        return carcass;
    }

    public void setCarcass(Carcass carcass) {
        this.carcass = carcass;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();
        Car car = session.get(Car.class, 2);
        Transmission transmission = session.get(Transmission.class, 1);
        System.out.println(car);
        System.out.println(car.hashCode());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carcass=" + carcass +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(name, car.name)
                && Objects.equals(carcass, car.carcass)
                && Objects.equals(engine, car.engine)
                && Objects.equals(transmission, car.transmission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, carcass, engine, transmission);
    }
}
