package com.company;

import java.util.ArrayList;

public class Student {

    private final String firstName;
    private final String surname;
    private final int indexNumber;
    private ArrayList<Integer> grades;

    public Student(String imie, String nazwisko, int numerIndeksu) {
        this.firstName = imie;
        this.surname = nazwisko;
        this.indexNumber = numerIndeksu;
        grades = new ArrayList<>();
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public float getSumOfGrades() {
        float sumOfGrades = 0f;

        if (grades.size() == 0) return 0.0f;
        for (int grade : grades) {
            sumOfGrades += grade;
        }
        return sumOfGrades;
    }

    public int getAmountOfGrades(){
        int amountOfGrades = 0;

        if (grades.size() == 0) return 0;
        for (int grade : grades) {
            amountOfGrades ++;
        }
        return amountOfGrades;
    }

    public void setAddGrade(int subjectGrade) {
        grades.add(subjectGrade);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getIndexNumber() {
        return indexNumber;
    }

    private String gradesToString() {

        String gradesToString = "";

        for (int i = 0; i < grades.size(); i++) {
            if (i != 0) gradesToString += ", ";
            gradesToString += grades.get(i);
        }
        return gradesToString;
    }

    public float getAverageGrade() {
        float divideBy = 0f, sumOfGrades = 0f;

        if (grades.size() == 0) return 0.0f;
        for (int grade : grades) {
            sumOfGrades += grade;
            divideBy++;
        }
        return sumOfGrades / divideBy;
    }

    @Override
    public String toString() {

        return "Imie i nazwisko: " + this.firstName + " " + this.surname
                + " Numer indeksu: " + this.indexNumber + " Oceny z kursu: " + gradesToString();
    }
}
