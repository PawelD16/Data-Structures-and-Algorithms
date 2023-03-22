package com.company;

import java.util.ArrayList;

public class Student {

    private String name;
    private String forename;
    private int age;
    private ArrayList<Integer> grades;

    public Student(String name, String forename, int age) {
        this.name = name;
        this.forename = forename;
        this.age = age;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public String getForename() {
        return forename;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    @Override
    public String toString() {
        return name + "|" + forename + grades.toString();
    }

    @Override
    public int hashCode() {
        return this.forename.hashCode() + this.name.hashCode() + this.age;
    }
}
