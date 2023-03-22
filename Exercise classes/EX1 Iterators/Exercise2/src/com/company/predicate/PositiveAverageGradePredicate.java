package com.company.predicate;

import com.company.Student;

public class PositiveAverageGradePredicate implements Predicate {
    @Override
    public boolean accept(Object arg) {
        if (!(arg instanceof Student)) return false;
        return ((Student) arg).getAverageGrade() >= 3.0;
    }
}
