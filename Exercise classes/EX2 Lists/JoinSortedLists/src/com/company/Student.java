package com.company;

public class Student{

    private final int index;
    private final String name, surname;

    public Student(int index, String name, String surname) {
        this.index = index;
        this.surname = surname;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\tsurame: " + surname +"\tindex: " + index;
    }
}
