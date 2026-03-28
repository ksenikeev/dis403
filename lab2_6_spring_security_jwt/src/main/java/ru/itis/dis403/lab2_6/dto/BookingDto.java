package ru.itis.dis403.lab2_6.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public class BookingDto {
    private Long id;
    private Date arrivaldate;
    private Date stayingdate;
    private Date departuredate;

    private Long personId;
    private String name;
    private String gender;
    private Date birthDate;

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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
