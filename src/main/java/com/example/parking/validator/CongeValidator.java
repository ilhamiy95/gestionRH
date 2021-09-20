package com.example.parking.validator;

import com.example.parking.dao.CongeDao;
import com.example.parking.model.Conge;
import com.example.parking.model.Personnel;
import com.example.parking.model.TypeConge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.example.parking.helper.DateDiff.numberOfDaysExcludingWeekend;

@Component
public class CongeValidator implements Validator {
    @Autowired
    private CongeDao congeDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Conge.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Conge conge = (Conge) o;
        ValidationUtils.rejectIfEmpty(errors, "dateDebut", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "dateFin", "NotEmpty");
        if (conge.getDateFin().before(conge.getDateDebut())) {
            errors.rejectValue("dateFin", "conge.datefin.invalid");
            return;
        }
        Personnel personnel = conge.getPersonnel();
        int numberOfDaysExcludingWeekend = numberOfDaysExcludingWeekend(conge.getDateDebut(), conge.getDateFin());
        System.out.println("numberOfDaysExcludingWeekend= " + numberOfDaysExcludingWeekend);
        if (numberOfDaysExcludingWeekend == 0) {
            errors.rejectValue("dateFin", "conge.datefin.weekend");
        }
        if (TypeConge.PAYE.equals(conge.getType()) && personnel.getSoldeConge() < numberOfDaysExcludingWeekend) {
            errors.rejectValue("type", "conge.type.soldeInsuffisant");
        }
    }
}
