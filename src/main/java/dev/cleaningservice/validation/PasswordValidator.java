package dev.cleaningservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private int size;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null || password.isBlank())
            return true;

        String regex = "^(?=.*[A-Z])(?=.*\\d).{" + size + ",}$";
        return password.matches(regex);
    }

    @Override
    public void initialize(Password passwordAnnotation) {
        size = passwordAnnotation.size();
    }
}
