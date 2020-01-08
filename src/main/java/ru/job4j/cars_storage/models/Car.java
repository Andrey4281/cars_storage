package ru.job4j.cars_storage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_carcass", foreignKey = @ForeignKey(name="cars_id_carcass_fkey"), nullable = false)
    private Carcass carcass;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_engine", foreignKey = @ForeignKey(name = "cars_id_engine_fkey"), nullable = false)
    private Engine engine;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_transmission", foreignKey = @ForeignKey(name = "cars_id_transmission_fkey"), nullable = false)
    private Transmission transmission;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner",
    joinColumns = @JoinColumn(name="car_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="driver_id", referencedColumnName = "id")
    )
    private Set<Driver> drivers;

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

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = factory.openSession();
        Car carOne = new Car();
        carOne.setName("CarForAndrey");
        Carcass carcass = new Carcass();
        carcass.setName("CarcassForAndrey");
        Engine engine = new Engine();
        engine.setName("EngineForAndrey");
        Transmission transmissionOne = new Transmission();
        transmissionOne.setName("TransmissionForAndrey");
        carOne.setCarcass(carcass);
        carOne.setEngine(engine);
        carOne.setTransmission(transmissionOne);
        Driver driver = new Driver();
        driver.setName("Andrey");
        carOne.setDrivers(new HashSet<>(Arrays.asList(driver)));
        driver.setCars(new HashSet<>(Arrays.asList(carOne)));
        session.beginTransaction();
        session.save(carcass);
        session.save(engine);
        session.save(transmissionOne);
        session.save(carOne);
        session.getTransaction().commit();

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

}
