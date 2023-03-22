package com.company;

import java.util.LinkedList;
import java.util.ListIterator;

public class Simulator {

    private OneWayLinkedList<Integer> list;
    private ListIterator<Integer> listIterator;
    private int first, n, k;

    public Simulator(int n, int k) {

        if (n > k) {
            list = new OneWayLinkedList<>();
            first = (k - 1) % (n + 1);
            this.n = n;
            this.k = k;

            for (int i = 1; i < n + 1; i++) {
                list.add(i);
            }

            /*
            listIterator = list.listIterator();
            System.out.println("Lista");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(listIterator.next());
            }

             */

            System.out.println("\n\n\n");

            list.circulate();
            listIterator = list.listIterator();

            for (int i = 0; i < first; i++) listIterator.next();
            listIterator.remove();

            while (list.size() >= k) {
                for (int i = 0; i < k; i++) listIterator.next();
                //System.out.println(listIterator.next());
                listIterator.remove();
            }


            System.out.println("Przeżyły numery:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(listIterator.next());
            }
        } else System.out.println("Nie zginie nikt!");
        ;
    }

}
