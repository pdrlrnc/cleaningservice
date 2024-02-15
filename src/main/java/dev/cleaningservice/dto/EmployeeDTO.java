package dev.cleaningservice.dto;

import dev.cleaningservice.validation.MinAge;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class EmployeeDTO {

    private Long userEmployeeId;

    private String username;

    @NotNull(message = "please insert a valid number")
    private int yearsOfExperience;

    @NotEmpty(message = "please insert your full name")
    private String fullName;

    @NotNull(message = "please insert your date of birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @MinAge
    private Date dateOfBirth;

    @NotEmpty(message = "please insert your phone number")
    private String phoneNumber;

    @NotEmpty(message = "please insert your address")
    private String address;

    @NotNull(message = "please insert your social security number")
    private int socialSecurityNumber;

    public EmployeeDTO() {
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserEmployeeId() {
        return userEmployeeId;
    }

    public void setUserEmployeeId(Long userEmployeeId) {
        this.userEmployeeId = userEmployeeId;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "yearsOfExperience=" + yearsOfExperience +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
