package dev.cleaningservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidator implements ConstraintValidator<MinAge, Date> {

    private int minAge;

    @Override
    public void initialize(MinAge minAgeAnnotation) {
        minAge = minAgeAnnotation.age();
    }

    @Override
    public boolean isValid(Date dateOfBirth, ConstraintValidatorContext context) {

        if(dateOfBirth == null)
            return true;

        LocalDate localDateOfBirth = dateOfBirth.toLocalDate();
        Period age = Period.between(localDateOfBirth, LocalDate.now());
        return age.getYears() >= minAge;
    }
}
