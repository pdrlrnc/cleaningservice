package dev.cleaningservice.dto;

import dev.cleaningservice.validation.MinAge;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class ProfileDTO {

    private Long userInfoId;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "First name is required")
    @Length(min = 1, message = "First name is required")
    private String firstName;

    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @MinAge
    private Date dateOfBirth;

    @Pattern(regexp = "^(9[1236]\\d) ?(\\d{3}) ?(\\d{3})$", message = "Phone number must be valid")
    private String phoneNumber;

    private String address;

    private Date startedWorking;

    private int yearsOfExperience;

    public ProfileDTO() {
    }

    public ProfileDTO(Long userInfoId, String username, String email, String firstName, String fullName, Date dateOfBirth, String phoneNumber, String address) {
        this.userInfoId = userInfoId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "userInfoId=" + userInfoId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", startedWorking=" + startedWorking +
                ", yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
