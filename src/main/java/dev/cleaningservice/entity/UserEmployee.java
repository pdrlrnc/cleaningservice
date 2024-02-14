package dev.cleaningservice.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "employee")
public class UserEmployee extends UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "started_working")
    private Date startedWorking;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @Column(name = "social_security_number")
    private int socialSecurityNumber;

    public UserEmployee() {
    }

    public UserEmployee(Long id, Date startedWorking, int yearsOfExperience, int socialSecurityNumber) {
        this.id = id;
        this.startedWorking = startedWorking;
        this.yearsOfExperience = yearsOfExperience;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartedWorking() {
        return startedWorking;
    }

    public void setStartedWorking(Date startedWorking) {
        this.startedWorking = startedWorking;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public String toString() {
        return "UserEmployee{" +
                "id=" + id +
                ", startedWorking=" + startedWorking +
                ", yearsOfExperience=" + yearsOfExperience +
                ", socialSecurityNumber=" + socialSecurityNumber +
                '}';
    }
}
