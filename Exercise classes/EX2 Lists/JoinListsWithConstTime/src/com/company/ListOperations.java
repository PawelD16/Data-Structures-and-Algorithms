package com.company;

import java.util.ListIterator;

public class ListOperations<E> {
    public DoublyLinkedList<E> concatenateTwoListsToOne(DoublyLinkedList<E> l1, DoublyLinkedList<E> l2) {

        ListIterator<?> l2ListIterator = l2.listIterator();
        for (E element : l2) {
            l1.add(element);
        }

        return l1;
    }

    public DoublyLinkedList<E> addBeforeValue(DoublyLinkedList<E> l1, DoublyLinkedList<E> l2, E value) {
        int index;
        if(l1.contains(value))  index = l1.indexOf(value);
        else return l1;

        for (E element : l2) {
            l1.add(index, element);
        }
        return l1;
    }

    public DoublyLinkedList<E> addBeforeIndex(DoublyLinkedList<E> l1, DoublyLinkedList<E> l2, int index) {
        if(index < 0 || index > l1.size()) throw new IndexOutOfBoundsException();

        for (E element : l2) {
            l1.add(index, element);
        }
        return l1;
    }
}

