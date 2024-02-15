package dev.cleaningservice.dto;

import dev.cleaningservice.validation.MinAge;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public class EmployeeDTO {

    private Long userEmployeeId;

    @NotEmpty(message = "please insert a valid number")
    private int yearsOfExperience;

    @NotEmpty(message = "please insert your full name")
    private String fullName;

    @NotEmpty(message = "please insert your date of birth")
    @MinAge(message = "to aply you should be at least 18 years old")
    private Date dateOfBirth;

    @NotEmpty(message = "please insert your phone number")
    private String phoneNumber;

    @NotEmpty(message = "please insert your address")
    private String address;

    @NotEmpty(message = "please insert your social security number")
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "yearsOfExperience=" + yearsOfExperience +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
