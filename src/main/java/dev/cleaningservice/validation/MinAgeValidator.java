package dev.cleaningservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

public class MinAgeValidator implements ConstraintValidator<MinAge, String> {

    private int minAge;

    @Override
    public void initialize(MinAge minAgeAnnotation) {
        minAge = minAgeAnnotation.age();
    }

    @Override
    public boolean isValid(String dateOfBirth, ConstraintValidatorContext context) {

        System.out.println("OLA");

        Date dateOfBirthAux = Date.valueOf(dateOfBirth);

        System.out.println("ADEU");

        if(dateOfBirthAux == null)
            return false;

        LocalDate localDateOfBirth = dateOfBirthAux.toLocalDate();
        Period age = Period.between(localDateOfBirth, LocalDate.now());
        return age.getYears() >= minAge;
    }
}
