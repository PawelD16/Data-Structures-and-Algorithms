package com.company;

public class Student {

    private final String firstName;
    private final String surname;
    private final int indexNumber;
    private int subjectGrade;

    public Student(String imie, String nazwisko, int numerIndeksu) {
        this.firstName = imie;
        this.surname = nazwisko;
        this.indexNumber = numerIndeksu;
        subjectGrade = 0;
    }

    public int getSubjectGrade() {
        return subjectGrade;
    }

    public void setSubjectGrade(int subjectGrade) {
        this.subjectGrade = subjectGrade;
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

    @Override
    public String toString() {

        return "Imie i nazwisko: " + this.firstName + " " + this.surname
                + " Numer indeksu: " + this.indexNumber + " Ocena z kursu: " + this.subjectGrade;
    }

    public boolean equals(Student o) {
        if (o.getIndexNumber() == getIndexNumber() && o.firstName == firstName && o.getSurname() == surname)
            return true;
        else return false;
    }
}
