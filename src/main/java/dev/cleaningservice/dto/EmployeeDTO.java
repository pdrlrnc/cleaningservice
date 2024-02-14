package dev.cleaningservice.dto;

import dev.cleaningservice.validation.MinAge;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public class EmployeeDTO {

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

}
