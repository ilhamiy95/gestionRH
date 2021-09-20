package com.example.parking.validator;

import com.example.parking.model.Absence;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.example.parking.helper.DateDiff.numberOfDaysExcludingWeekend;

@Component
public class AbsenceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Absence.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Absence absence = (Absence) o;
        ValidationUtils.rejectIfEmpty(errors, "dateDebut", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "dateFin", "NotEmpty");
        if (absence.getDateFin().before(absence.getDateDebut())) {
            errors.rejectValue("dateFin", "absence.datefin.invalid");
            return;
        }
        int numberOfDaysExcludingWeekend = numberOfDaysExcludingWeekend(absence.getDateDebut(), absence.getDateFin());
        System.out.println("numberOfDaysExcludingWeekend= " + numberOfDaysExcludingWeekend);
        if (numberOfDaysExcludingWeekend == 0) {
            errors.rejectValue("dateFin", "conge.datefin.weekend");
        }
    }
}
