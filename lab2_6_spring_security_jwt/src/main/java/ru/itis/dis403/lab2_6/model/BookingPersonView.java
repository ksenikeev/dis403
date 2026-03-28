package ru.itis.dis403.lab2_6.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "view_booking_person")
public class BookingPersonView {
    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date arrivaldate;

    @Temporal(TemporalType.DATE)
    private Date stayingdate;

    @Column(name = "hotel_id")
    private Long hotelId;

    private String name;

    private String gender;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

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

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
