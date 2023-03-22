package com.company;


import java.util.Comparator;

public class Main {


    public static OneWayLinkedList<Student> twoListsToOneStudentsSortedByIndex(OneWayLinkedList<Student> l1, OneWayLinkedList<Student> l2) {

        OneWayLinkedList<Student> students = new OneWayLinkedList<>(Comparator.comparingInt(Student::getIndex));

        for(Student student:l1){
            students.add(student);
        }

        for(Student student:l2){
            students.add(student);
        }

        students.sortLinkedList();

        return students;
    }

    public static void main(String[] args) {

        OneWayLinkedList<Student> students0 = new OneWayLinkedList<>(Comparator.comparingInt(Student::getIndex));

        OneWayLinkedList<Student> students1 = new OneWayLinkedList<>((o1, o2) -> {
            if (o1.getIndex() > o2.getIndex()) return 1;
            if(o1.getIndex() < o2.getIndex()) return -1;
            return 0;
        });


        students0.add(new Student(23213, "Piotr", "Nowak"));
        students0.add(new Student(22314, "Paweł", "Dudek"));
        students0.add(new Student(92753, "Mikołaj", "Kowalski"));
        students0.add(new Student(96031, "Michał", "Dąbek"));
        students0.add(new Student(32168, "Jakub", "Bździagwa"));




        students1.add(new Student(22131, "Otylia", "Łodyga"));
        students1.add(new Student(57843, "Agnieszka", "Bolączka"));
        students1.add(new Student(91236, "Gabriela", "Gąbka"));
        students1.add(new Student(83456, "Magdalena", "Magdaleńska"));
        students1.add(new Student(45232, "Jadwiga", "Zdruj"));

        students0 = twoListsToOneStudentsSortedByIndex(students0, students1);

        for(Student student:students0){
            System.out.println(student);
        }

    }
}
