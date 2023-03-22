package com.company;

import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
        OneWayLinkedList<String> oneWayLinkedList = new OneWayLinkedList();
        oneWayLinkedList.add("A");
        oneWayLinkedList.add("B");
        oneWayLinkedList.add("C");
        oneWayLinkedList.add("D");
        oneWayLinkedList.add("E");


        ListIterator<String> listIterator = oneWayLinkedList.listIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex() + ". " + listIterator.next());
        }
        System.out.println("\n\n");

        while (listIterator.hasPrevious()) {
            if(listIterator.previous().equals("E")) listIterator.set("G");
        }

        while (listIterator.hasNext()) {
            System.out.println(listIterator.nextIndex() + ". " + listIterator.next());
        }

        while (listIterator.hasPrevious()) {
            if(listIterator.previous().equals("A")) listIterator.remove();
        }


        while (listIterator.hasNext()) {
            if(listIterator.next().equals("C")) listIterator.add(":^)");
        }


        System.out.println("\n\n");

        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previousIndex() + ". " + listIterator.previous());
        }
    }
}
