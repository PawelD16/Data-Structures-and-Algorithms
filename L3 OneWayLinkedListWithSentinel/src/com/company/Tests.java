package com.company;

import java.util.ListIterator;

public class Tests {

    public Tests() {

        OneWayLinkedListWithSentinel<String> oneWayLinkedListWithSentinel0 = new OneWayLinkedListWithSentinel<>();
        OneWayLinkedListWithSentinel<Integer> oneWayLinkedListWithSentinel1 = new OneWayLinkedListWithSentinel<>();
        OneWayLinkedListWithSentinel<Integer> oneWayLinkedListWithSentinel2 = new OneWayLinkedListWithSentinel<>();
        OneWayLinkedListWithSentinel<String> oneWayLinkedListWithSentinel3 = new OneWayLinkedListWithSentinel<>();

        System.out.println("no elements: ");
        System.out.println("ifContains \"B\" :" + oneWayLinkedListWithSentinel0.contains("B"));
        System.out.println("ifEmpty: " + oneWayLinkedListWithSentinel0.isEmpty());
        System.out.println("size: " + oneWayLinkedListWithSentinel0.size());
        oneWayLinkedListWithSentinel0.reverse();

        oneWayLinkedListWithSentinel1.add(0, 2);
        System.out.println("\n\nifContains 2 :" + oneWayLinkedListWithSentinel1.contains(2));
        System.out.println("sout element 0: " + oneWayLinkedListWithSentinel1.get(0));
        System.out.println("indexOf 2: " + oneWayLinkedListWithSentinel1.indexOf(2));
        System.out.println("ifEmpty: " + oneWayLinkedListWithSentinel1.isEmpty());
        System.out.println("size: " + oneWayLinkedListWithSentinel1.size());
        oneWayLinkedListWithSentinel1.reverse();

        System.out.println("\n\n\n 4 element: ");

        oneWayLinkedListWithSentinel2.add(2);
        oneWayLinkedListWithSentinel2.add(3);
        oneWayLinkedListWithSentinel2.add(4);
        oneWayLinkedListWithSentinel2.add(7);
        System.out.println("ifContains 2 :" + oneWayLinkedListWithSentinel2.contains(2));
        System.out.println("sout element 0: " + oneWayLinkedListWithSentinel2.get(0));
        System.out.println("indexOf 2: " + oneWayLinkedListWithSentinel2.indexOf(2));
        System.out.println("ifEmpty: " + oneWayLinkedListWithSentinel2.isEmpty());
        System.out.println("size: " + oneWayLinkedListWithSentinel2.size());

        ListIterator<Integer> owllws2ListIterator = oneWayLinkedListWithSentinel2.listIterator();
        System.out.println("Print elements: ");

        while (owllws2ListIterator.hasNext()) {
            System.out.println(owllws2ListIterator.nextIndex() + ". " + owllws2ListIterator.next());
        }

        oneWayLinkedListWithSentinel2.reverse();

        owllws2ListIterator = oneWayLinkedListWithSentinel2.listIterator();
        System.out.println("Print elements after reverse: ");

        while (owllws2ListIterator.hasNext()) {
            System.out.println(owllws2ListIterator.nextIndex() + ". " + owllws2ListIterator.next());
        }



        System.out.println("\n\n\n7 element: ");

        oneWayLinkedListWithSentinel3.add("A");
        oneWayLinkedListWithSentinel3.add("C");
        oneWayLinkedListWithSentinel3.add("XD");
        oneWayLinkedListWithSentinel3.add(0,"TEST");
        oneWayLinkedListWithSentinel3.add(4, "o");
        oneWayLinkedListWithSentinel3.add(":D");
        oneWayLinkedListWithSentinel3.add("asda");
        System.out.println("ifContains A :" + oneWayLinkedListWithSentinel3.contains("A"));
        System.out.println("sout element 0: " + oneWayLinkedListWithSentinel3.get(0));
        System.out.println("indexOf \"TEST\": " + oneWayLinkedListWithSentinel3.indexOf("TEST"));
        System.out.println("ifEmpty: " + oneWayLinkedListWithSentinel3.isEmpty());
        System.out.println("size: " + oneWayLinkedListWithSentinel3.size());

        /*
        OneWayLinkedListWithSentinel<String> owllws0 = new OneWayLinkedListWithSentinel();
        OneWayLinkedListWithSentinel<Integer> owllws1 = new OneWayLinkedListWithSentinel();
        OneWayLinkedListWithSentinel<Integer> owllws2 = new OneWayLinkedListWithSentinel();
        OneWayLinkedListWithSentinel<String> owllws3 = new OneWayLinkedListWithSentinel();


        System.out.println("no elements: ");
        System.out.println("ifContains \"B\" :" + owllws0.contains("B"));
        System.out.println("ifEmpty: " + owllws0.isEmpty());
        System.out.println("size: " + owllws0.size());

        ListIterator<String> owllws0ListIterator = owllws0.listIterator();
        System.out.println("Print elements: ");
        while (owllws0ListIterator.hasNext()) {
            System.out.println(owllws0ListIterator.nextIndex() + ". " + owllws0ListIterator.next());
        }

        System.out.println("\n\n\n1 element: ");

        owllws1.add(0, 2);
        System.out.println("ifContains 2 :" + owllws1.contains(2));
        System.out.println("sout element 0: " + owllws1.get(0));
        System.out.println("indexOf 2: " + owllws1.indexOf(2));
        System.out.println("ifEmpty: " + owllws1.isEmpty());
        System.out.println("size: " + owllws1.size());

        ListIterator<Integer> owllws1ListIterator = owllws1.listIterator();
        System.out.println("Print elements: ");

        while (owllws1ListIterator.hasNext()) {
            System.out.println(owllws1ListIterator.nextIndex() + ". " + owllws1ListIterator.next());
        }

        owllws1ListIterator = owllws1.listIterator();
        while (owllws1ListIterator.hasNext()) {
            owllws1ListIterator.next();
            owllws1ListIterator.set(-3);
        }

        System.out.println("Print elements after setting them to -3: ");
        owllws1ListIterator = owllws1.listIterator();
        while (owllws1ListIterator.hasNext()) {
            System.out.println(owllws1ListIterator.nextIndex() + ". " + owllws1ListIterator.next());
        }


        System.out.println("\n\n\n 4 element: ");

        owllws2.add(2);
        owllws2.add(3);
        owllws2.add(4);
        owllws2.add(7);
        System.out.println("ifContains 2 :" + owllws2.contains(2));
        System.out.println("sout element 0: " + owllws2.get(0));
        System.out.println("indexOf 2: " + owllws2.indexOf(2));
        System.out.println("ifEmpty: " + owllws2.isEmpty());
        System.out.println("size: " + owllws2.size());

        ListIterator<Integer> owllws2ListIterator = owllws2.listIterator();
        System.out.println("Print elements: ");

        while (owllws2ListIterator.hasNext()) {
            System.out.println(owllws2ListIterator.nextIndex() + ". " + owllws2ListIterator.next());
        }
        System.out.println("After adding element with iterator");

        owllws2ListIterator = owllws2.listIterator();
        int i = 0;
        while (owllws2ListIterator.hasNext() && i<5) {
            owllws2ListIterator.next();
            owllws2ListIterator.add(i+10);
            i++;
        }

        owllws2ListIterator.next();
        owllws2ListIterator.remove();

        while(owllws2ListIterator.hasPrevious()) owllws2ListIterator.previous();
        while (owllws2ListIterator.hasNext()) {
            System.out.println(owllws2ListIterator.nextIndex() + ". " + owllws2ListIterator.next());
        }


        System.out.println("\n\n\n7 element: ");

        owllws3.add("A");
        owllws3.add("C");
        owllws3.add("XD");
        owllws3.add("TEST");
        owllws3.add("o");
        owllws3.add(":D");
        owllws3.add("asda");
        System.out.println("ifContains A :" + owllws3.contains("A"));
        System.out.println("sout element 0: " + owllws3.get(0));
        System.out.println("indexOf \"TEST\": " + owllws3.indexOf("TEST"));
        System.out.println("ifEmpty: " + owllws3.isEmpty());
        System.out.println("size: " + owllws3.size());

        ListIterator<String> owllws3ListIterator = owllws3.listIterator();
        System.out.println("Print elements: ");

        while (owllws3ListIterator.hasNext())
            System.out.println(owllws3ListIterator.nextIndex() + ". " + owllws3ListIterator.next());


        owllws3ListIterator = owllws3.listIterator();
        while (owllws3ListIterator.hasNext()) {
            owllws3ListIterator.next();
        }


        owllws3ListIterator.remove();


        owllws3ListIterator.previous();
        owllws3ListIterator.previous();
        owllws3ListIterator.next();
        owllws3ListIterator.remove();
        System.out.println(owllws3ListIterator.previous());
        owllws3ListIterator.add("added after");

        System.out.println("After deletion of some elements and addition of other");
        while(owllws3ListIterator.hasPrevious()) owllws3ListIterator.previous();
        while (owllws3ListIterator.hasNext()) {
            System.out.println(owllws3ListIterator.nextIndex() + ". " + owllws3ListIterator.next());
        }
         */

    }
}
