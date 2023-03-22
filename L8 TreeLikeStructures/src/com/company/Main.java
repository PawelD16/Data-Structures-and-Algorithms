package com.company;

import com.company.sorting.PriorityQueueSorter;
import com.company.generators.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 4000;

        System.out.println("for N = " + n);
        ArrayHeap<Integer> arrayHeap = new ArrayHeap<>((int) Math.sqrt((Math.sqrt(n))), Integer::compareTo);
        List<Integer> listArrayHeap = new ShuffledIntegerArrayGenerator().generate(n);
        PriorityQueueSorter<Integer> priorityQueueSorterArrayHeap = new PriorityQueueSorter<>(arrayHeap);
        priorityQueueSorterArrayHeap.sort(listArrayHeap);
        priorityQueueSorterArrayHeap.printStatistic("ArrayHeap");


        TreeHeap<Integer> treeHeap = new TreeHeap<>(Integer::compareTo);
        List<Integer> listTreeHeap = new ShuffledIntegerArrayGenerator().generate(n);
        PriorityQueueSorter<Integer> priorityQueueSorterTreeHeap = new PriorityQueueSorter<>(treeHeap);
        priorityQueueSorterTreeHeap.sort(listTreeHeap);
        priorityQueueSorterTreeHeap.printStatistic("TreeHeap");

        /*
        ArrayHeap<Integer> arrayHeap = new ArrayHeap<>(10, Integer::compareTo);
        arrayHeap.add(55);
        arrayHeap.add(15);
        arrayHeap.add(57);
        arrayHeap.add(12);
        arrayHeap.add(50);
        arrayHeap.add(20);
        arrayHeap.add(77);
        arrayHeap.add(43);
        arrayHeap.add(85);
        arrayHeap.add(13);

        arrayHeap.print();

        for (int i = arrayHeap.getSize(); i > 0; i--)
            System.out.println(arrayHeap.minimum());

        System.out.println("\n\n");

        TreeHeap<Integer> treeHeap = new TreeHeap<>(Integer::compareTo);
        treeHeap.add(55);
        treeHeap.add(15);
        treeHeap.add(57);
        treeHeap.add(12);
        treeHeap.add(50);
        treeHeap.add(20);
        treeHeap.add(77);
        treeHeap.add(43);
        treeHeap.add(85);
        treeHeap.add(13);

        treeHeap.print();

        for (int i = treeHeap.getSize(); i > 0; i--)
            System.out.println(treeHeap.minimum());
*/
    }
}
