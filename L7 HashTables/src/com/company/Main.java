package com.company;

import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        OpenAddressingHashTable<Student> openStudent = new OpenAddressingHashTable<>(0.6, new StudentComparator(), Student::hashCode, (object, trial) -> trial);

        openStudent.insert(new Student("Pawel", "Dudek", 19));
        openStudent.insert(new Student("Piotr", "Nowak", 18));
        openStudent.insert(new Student("Grzegorz", "Bułka", 20));
        openStudent.insert(new Student("Andrzej", "Dudek", 21));
        openStudent.insert(new Student("Tomasz", "Brzdąc", 22));
        openStudent.insert(new Student("Eugeniusz", "Leśny", 19));
        openStudent.insert(new Student("Pawel", "Dudek", 19));

        giveInfo(openStudent);

        System.out.println(openStudent);


        /*
        int n = 100;

        simulateForAlfa(0.1, n);
        simulateForAlfa(0.2, n);
        simulateForAlfa(0.5, n);
        simulateForAlfa(0.9, n);

         */
    }

    public static void simulateForAlfa(double alfa, int n) {
        System.out.println("***********************************************************************************************************************\nFor alfa " + alfa + "\n");
        OpenAddressingHashTable<Integer> openAddressingHashTableWithLinearProbing = new OpenAddressingHashTable<>(alfa, Comparator.comparingInt(o -> o), object -> object, (object, trial) -> trial);
        addToHashTable(openAddressingHashTableWithLinearProbing, n);
        System.out.println("Open addressing hash table with linear probing\n" + openAddressingHashTableWithLinearProbing);
        giveInfo(openAddressingHashTableWithLinearProbing);

        OpenAddressingHashTable<Integer> openAddressingHashTableWithQuadraticProbing = new OpenAddressingHashTable<>(alfa, Comparator.comparingInt(o -> o), object -> object, (object, trial) -> (int) (Math.pow(-1, trial - 1) * Math.pow(((trial + 1) / 2), 2)));
        addToHashTable(openAddressingHashTableWithQuadraticProbing, n);
        System.out.println("Open addressing hash table with quadratic probing:\n" + openAddressingHashTableWithQuadraticProbing);
        giveInfo(openAddressingHashTableWithQuadraticProbing);

        SeparateChainingHashTable<Integer> separateChainingHashTable = new SeparateChainingHashTable<>(alfa, Comparator.comparingInt(o -> o), object -> object);
        addToHashTable(separateChainingHashTable, n);
        System.out.println("Separate chaining hash table:\n" + separateChainingHashTable);
        giveInfo(separateChainingHashTable);

        System.out.println("***********************************************************************************************************************");
    }

    public static void addToHashTable(HashTable<Integer> hashTable, int n) {
        for (int i = 0; i < n; i++) hashTable.insert(new Random().nextInt(n));
    }

    public static void giveInfo(HashTable<?> hashTable) {
        System.out.println("Load factor: " + hashTable.loadFactor() + " capacity: " + hashTable.capacity() + " size: " + hashTable.size() + " collisions: " + hashTable.collisions() + " hashFunctionEvaluations: " + hashTable.hashFunctionEvaluations() + " insertComparisons: " + hashTable.insertComparisons() + " ifContainsRepeats: " + hashTable.ifContainsRepeats() + "\n");
    }
}
