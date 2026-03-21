package ru.itis.dis403.lab2_6.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Booking {
    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date arrivaldate;

    @Temporal(TemporalType.DATE)
    private Date stayingdate;

    @Temporal(TemporalType.DATE)
    private Date departuredate;

    @ManyToOne
    private Hotel hotel;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public Date getStayingdate() {
        return stayingdate;
    }

    public void setStayingdate(Date stayingdate) {
        this.stayingdate = stayingdate;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
