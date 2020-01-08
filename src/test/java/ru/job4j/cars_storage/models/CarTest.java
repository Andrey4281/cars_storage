package ru.job4j.cars_storage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private SessionFactory factory;

    @BeforeEach
    void init() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @Test
    void whenAddCarShouldGetIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Car carOne = new Car();
        carOne.setName("CarForAndrey");
        Carcass carcass = new Carcass("CarcassForAndrey");
        Engine engine = new Engine("EngineForAndrey");
        Transmission transmissionOne = new Transmission("TransmissionForAndrey");
        carOne.setCarcass(carcass);
        carOne.setEngine(engine);
        carOne.setTransmission(transmissionOne);
        Driver driver = new Driver();
        driver.setName("Andrey");
        carOne.setDrivers(new HashSet<>(Arrays.asList(driver)));
        driver.setCars(new HashSet<>(Arrays.asList(carOne)));

        Integer id = (Integer) session.save(carOne);
        session.getTransaction().commit();
        Car actual = session.get(Car.class, id);

        assertAll(
                () -> assertEquals(carOne, actual),
                () -> assertArrayEquals(carOne.getDrivers().toArray(), actual.getDrivers().toArray())
        );
    }

    @Test
    void whenRemoveCarShouldNotGetIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Car carOne = new Car();
        carOne.setName("CarForAndrey");
        Carcass carcass = new Carcass("CarcassForAndrey");
        Engine engine = new Engine("EngineForAndrey");
        Transmission transmissionOne = new Transmission("TransmissionForAndrey");
        carOne.setCarcass(carcass);
        carOne.setEngine(engine);
        carOne.setTransmission(transmissionOne);
        Driver driver = new Driver();
        driver.setName("Andrey");
        carOne.setDrivers(new HashSet<>(Arrays.asList(driver)));
        driver.setCars(new HashSet<>(Arrays.asList(carOne)));

        Integer id = (Integer) session.save(carOne);
        session.getTransaction().commit();
        session.beginTransaction();
        session.createQuery("delete Car where id=:param")
                .setParameter("param", id).executeUpdate();
        session.getTransaction().commit();
        List<Car> actual = session.createQuery("from Car where id=:param")
                .setParameter("param", id).list();

        assertTrue(actual.isEmpty());
    }

    @Test
    void whenUpdateCarShouldGetUpdatedCar() {
        Session session = factory.openSession();
        session.beginTransaction();
        Car carOne = new Car();
        carOne.setName("CarForAndrey");
        Carcass carcass = new Carcass("CarcassForAndrey");
        Engine engine = new Engine("EngineForAndrey");
        Transmission transmissionOne = new Transmission("TransmissionForAndrey");
        carOne.setCarcass(carcass);
        carOne.setEngine(engine);
        carOne.setTransmission(transmissionOne);
        Driver driver = new Driver();
        driver.setName("Andrey");
        carOne.setDrivers(new HashSet<>(Arrays.asList(driver)));
        driver.setCars(new HashSet<>(Arrays.asList(carOne)));

        Integer id = (Integer) session.save(carOne);
        session.getTransaction().commit();
        carOne.setId(id);
        carOne.setName("Volvo");
        carOne.setDrivers(new HashSet<>(Arrays.asList(new Driver("Eugeni"))));
        carOne.setEngine(new Engine("The best engine"));
        session.beginTransaction();
        session.saveOrUpdate(carOne);
        session.getTransaction().commit();
        Car actual = (Car) session.createQuery("from Car where id=:param")
                .setParameter("param", id).list().get(0);

        assertEquals(carOne, actual);
    }

}