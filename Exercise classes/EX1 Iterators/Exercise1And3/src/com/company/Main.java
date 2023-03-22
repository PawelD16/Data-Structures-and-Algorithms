package com.company;

import com.company.iterator.BasicIterator;
import com.company.iterator.GradeByIndexIterator;
import com.company.iterator.GradeFilterIterator;
import com.company.iterator.Iterator;

public class Main {

    public static BasicIterator addStudent(BasicIterator iterator, Student student) {
        Student[] students = new Student[iterator.getArray().length + 1];

        if (iterator.getPos() != 0) return iterator;
        while (iterator.hasNext()) {
            students[iterator.getPos()] = (Student) iterator.next();
        }
        students[students.length - 1] = student;

        return new BasicIterator(students);
    }

    public static Iterator deleteStudent(Iterator iterator, Student student) {
        Student[] students = new Student[iterator.getArray().length];
        Student student1;
        int i = 0;
        boolean ifContains = false;

        while (iterator.hasNext() && i < students.length) {
            student1 = (Student) iterator.next();

            if (student1.equals(student)) ifContains = true;
            else {
                students[i] = student1;
                i++;
            }
        }

        if (ifContains) {
            Student[] studentsFinal = new Student[students.length - 1];

            System.arraycopy(students, 0, studentsFinal, 0, studentsFinal.length);
            return new BasicIterator(studentsFinal);
        }
        return new BasicIterator(iterator.getArray());
    }

    public static BasicIterator bubbleSortByGrades(BasicIterator iterator) {

        BasicIterator secondIterator = new BasicIterator(iterator.getArray());
        int i = 0;

        while (iterator.hasNext() && iterator.getArray().length > i) {
            secondIterator = new BasicIterator(secondIterator.getArray());
            iterator.next();
            while (iterator.hasNext()) {
                if (((Student) iterator.next()).getSubjectGrade() > ((Student) secondIterator.next()).getSubjectGrade() && (secondIterator.getPos() < secondIterator.getArray().length) && (iterator.getPos() < iterator.getArray().length))
                    secondIterator.swap();
            }
            iterator = new BasicIterator(secondIterator.getArray());
            i++;
        }
        return new BasicIterator(secondIterator.getArray());
    }

    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student("Piotr", "Nowak", 202135); //creating random students
        students[1] = new Student("Pawel", "Dziup", 204213);
        students[2] = new Student("Grzegorz", "Brzeczyszczykiewicz", 208219);
        students[3] = new Student("Maciej", "Nowotny", 321412);
        students[4] = new Student("Agnieszka", "Kowalska", 745323);

        Iterator iterator = new BasicIterator(students); //show all students
        System.out.println("All students: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = new GradeByIndexIterator(students, 321412, 4); //adding grades
        iterator = new GradeByIndexIterator(students, 745323, 5);
        iterator = new GradeByIndexIterator(students, 204213, 3);

        iterator = new BasicIterator(students); //show all students
        System.out.println("\nAll students: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = new GradeFilterIterator(new BasicIterator(students), arg -> { //positive grade filter
            if (!(arg instanceof Student)) return false;
            return ((Student) arg).getSubjectGrade() >= 3.0;
        });
        float sumOfGrades = 0f, divideBy = 0f; //get the average and display it
        while (iterator.hasNext()) {
            Student student0 = (Student) iterator.next();
            sumOfGrades += student0.getSubjectGrade();
            divideBy++;
        }
        System.out.println("\n\nAverage grade of students who passed: " + sumOfGrades / divideBy + "\n\n");


        System.out.println("Students who didn't pass: "); //list students who didn't pass with filter iterator
        iterator = new GradeFilterIterator(new BasicIterator(students), arg -> {
            if (!(arg instanceof Student)) return false;
            return ((Student) arg).getSubjectGrade() < 3.0;
        });
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n\nAfter adding a student: "); //add students and list them
        iterator = addStudent(new BasicIterator(students), new Student("Justyna", "Justyńska", 382340));
        students = (Student[]) iterator.getArray();
        iterator = new BasicIterator(students);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = new GradeFilterIterator(new BasicIterator(students), arg -> { //positive grade filter
            if (!(arg instanceof Student)) return false;
            return ((Student) arg).getSubjectGrade() >= 3.0;
        });
        System.out.println("\n\nAfter deleting a student: "); //delete students (but with a filter) and list them
        iterator = deleteStudent(iterator, new Student("Agnieszka", "Kowalska", 745323));
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = new BasicIterator(students);
        iterator = bubbleSortByGrades((BasicIterator) iterator);

        System.out.println("\n\nAfter bubble sort: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
