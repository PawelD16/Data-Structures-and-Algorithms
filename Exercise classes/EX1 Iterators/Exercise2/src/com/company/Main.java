package com.company;

import com.company.iterator.AddGradeByIndexIterator;
import com.company.iterator.BasicIterator;
import com.company.iterator.GradeFilterIterator;
import com.company.iterator.Iterator;
import com.company.predicate.PositiveAverageGradePredicate;

public class Main {

    public static void main(String[] args) {
        Iterator iterator;
        Student[] students = new Student[5];

        students[0] = new Student("Piotr", "Nowak", 202135);
        students[1] = new Student("Pawel", "Dziup", 204213);
        students[2] = new Student("Grzegorz", "Brzeczyszczykiewicz", 208219);
        students[3] = new Student("Maciej", "Nowotny", 321412);
        students[4] = new Student("Agnieszka", "Kowalska", 745323);

        iterator = new AddGradeByIndexIterator(students, 321412, 4);
        iterator = new AddGradeByIndexIterator(students, 745323, 5);
        iterator = new AddGradeByIndexIterator(students, 745323, 5);
        iterator = new AddGradeByIndexIterator(students, 745323, 5);
        iterator = new AddGradeByIndexIterator(students, 204213, 2);

        iterator = new BasicIterator(students);

        System.out.println("All students: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = new GradeFilterIterator(new BasicIterator(students), new PositiveAverageGradePredicate());

        float divideBy = 0.0f, sumOfAverageGrades = 0.0f;
        while (iterator.hasNext()) {
            Student student0 = (Student) iterator.next();
            sumOfAverageGrades += student0.getAverageGrade();
            divideBy++;
        }

        System.out.println("\n\nAverage grade from students average grades: " + sumOfAverageGrades / divideBy);

        iterator = new GradeFilterIterator(new BasicIterator(students), new PositiveAverageGradePredicate());

        int amountOfGrades = 0;
        double sumOfGrades = 0.0;

        while (iterator.hasNext()) {
            Student student0 = (Student) iterator.next();
            sumOfGrades += student0.getSumOfGrades();
            amountOfGrades+= student0.getAmountOfGrades();
        }

        System.out.println("\n\nAverage grade from all grades (of students that average 3.0 or more): " + sumOfGrades / (float) amountOfGrades + "\n\n");


        iterator = new GradeFilterIterator(new BasicIterator(students), arg -> {
            if (!(arg instanceof Student)) return false;
            return ((Student) arg).getAverageGrade() < 3.0;
        });

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
