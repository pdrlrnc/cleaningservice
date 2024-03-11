package dev.cleaningservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfo client;

    @ManyToOne
    private UserEmployee employee;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "hours")
    private int hours;

    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserInfo getClient() {
        return client;
    }

    public void setClient(UserInfo client) {
        this.client = client;
    }

    public UserEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(UserEmployee employee) {
        this.employee = employee;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDateTime getEndDate(){
        return startDate.plusHours(hours);
    }

    public boolean isSameTimeFrame(Appointment appointmentArg){
        return !(this.getEndDate().isBefore(appointmentArg.getStartDate()) || this.getStartDate().isAfter(appointmentArg.getEndDate()));
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", client=" + client +
                ", employee=" + employee +
                ", startDate=" + startDate +
                ", hours=" + hours +
                '}';
    }
}
