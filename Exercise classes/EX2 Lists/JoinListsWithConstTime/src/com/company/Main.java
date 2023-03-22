package com.company;

import java.util.ListIterator;

public class Main {



    public static void main(String[] args) {

        DoublyLinkedList<String> doublyLinkedListA = new DoublyLinkedList<>();
        doublyLinkedListA.add("A");
        doublyLinkedListA.add("A");
        doublyLinkedListA.add("Q");
        doublyLinkedListA.add("A");
        ListIterator<String> doublyLinkedListAListIterator = doublyLinkedListA.listIterator();

        DoublyLinkedList<String> doublyLinkedListB = new DoublyLinkedList<>();
        doublyLinkedListB.add("B");
        doublyLinkedListB.add("B");
        doublyLinkedListB.add("B");


        while (doublyLinkedListAListIterator.hasNext()) {
            System.out.println(doublyLinkedListAListIterator.nextIndex() + ". " + doublyLinkedListAListIterator.next());
        }

        System.out.println("\n\n");



        //doublyLinkedListA = new ListOperations<String>().concatenateTwoListsToOne(doublyLinkedListA,doublyLinkedListB);
        //doublyLinkedListAListIterator = doublyLinkedListA.listIterator();

        doublyLinkedListA = new ListOperations<String>().addBeforeIndex(doublyLinkedListA , doublyLinkedListB, 1);
        doublyLinkedListAListIterator = doublyLinkedListA.listIterator();

        //doublyLinkedListA = new ListOperations<String>().addBeforeValue(doublyLinkedListA , doublyLinkedListB, "Q");
        //doublyLinkedListAListIterator = doublyLinkedListA.listIterator();

        while (doublyLinkedListAListIterator.hasNext()) {
            System.out.println(doublyLinkedListAListIterator.nextIndex() + ". " + doublyLinkedListAListIterator.next());
        }


    }
}
