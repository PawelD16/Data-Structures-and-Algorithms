package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getForename().compareTo(o2.getForename()) != 0) return 1;

        if (o1.getName().compareTo(o2.getName()) != 0) return 1;

        ArrayList<Integer> o1Grades = o1.getGrades();
        ArrayList<Integer> o2Grades = o2.getGrades();

        if (o1Grades.size() != o2Grades.size()) return 1;
        else for (int i = 0; i < o1.getGrades().size(); i++) {
            if (thenComparingInt(o1Grades.get(i), o2Grades.get(i)) != 0) return 1;
        }

        return 0;
    }

    private int thenComparingInt(Integer integer, Integer integer1) {
        return integer - integer1;
    }
}
