package dev.cleaningservice.dto;

import dev.cleaningservice.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegistrationDTO {

    @NotEmpty(message = "username is required!")
    private String username;

    @NotEmpty(message = "password is required!")
    @Password(message = "invalid password")
    private String password;

    @NotEmpty(message = "email is required!")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "first name is required!")
    private String firstName;

    public RegistrationDTO(){

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
}
