package dev.cleaningservice.entity;

import dev.cleaningservice.validation.MinAge;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity(name = "user_detail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_detail_id")
    private int id;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email")
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    @NotEmpty(message = "Username is required")
    @Length(min = 1, message = "Username is required")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Password is required")
    @Length(min = 5, message = "Password should be at least 5 characters long")
    private String password;

    @Column(name = "first_name")
    @NotEmpty(message = "First name is required")
    @Length(min = 1, message = "First name is required")
    private String firstName;

    @Column(name = "full_name")
    private String fullName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @MinAge
    private Date dateOfBirth;

    @Column(name = "phone_number")
    @Pattern(regexp = "^(9[1236]\\d) ?(\\d{3}) ?(\\d{3})$", message = "Phone number must be valid")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "photo")
    private String photo;

    public UserDetail() {
    }

    public UserDetail(int id, String email, String username, String password, String firstName, String fullName, Date dateOfBirth, String phoneNumber, String address, String photo) {
        System.out.println(dateOfBirth);
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}