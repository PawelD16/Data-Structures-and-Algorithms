package com.company;

public class Tests {

    public Tests() {


        System.out.println("CELLS PER NODE ARE UNEVEN");
        TwoWayLinkedList<String> twoWayLinkedList = new TwoWayLinkedList<>(3);
        twoWayLinkedList.add("A");
        twoWayLinkedList.add("B");
        twoWayLinkedList.add("C");
        twoWayLinkedList.add("D");
        twoWayLinkedList.add("E");
        twoWayLinkedList.add("F");
        twoWayLinkedList.add("G");
        twoWayLinkedList.add("H");
        twoWayLinkedList.add("I");
        twoWayLinkedList.add("J");
        twoWayLinkedList.add("K");
        twoWayLinkedList.add("L");


        System.out.println("Removed: \n" + twoWayLinkedList.remove("D"));
        System.out.println(twoWayLinkedList.remove("A"));
        System.out.println(twoWayLinkedList.remove("B"));
        System.out.println(twoWayLinkedList.remove("C"));
        System.out.println("Modification: " + twoWayLinkedList.set(7, "TESTER") + " turned to " + twoWayLinkedList.get(7));
        System.out.println("Get value on index 2: " + twoWayLinkedList.get(2));
        System.out.println("Size before adding: " + twoWayLinkedList.size());

        twoWayLinkedList.add(0, "A1");
        twoWayLinkedList.add(4, "A2");
        twoWayLinkedList.add(4, "A3");
        twoWayLinkedList.add(4, "A4");
        twoWayLinkedList.add(4, "A5");
        twoWayLinkedList.add(4, "A6");
        twoWayLinkedList.add(4, "A7");

        twoWayLinkedList.add(twoWayLinkedList.size() - 1, "X");
        twoWayLinkedList.add(16, "Y");
        //twoWayLinkedList.add(17, "Z");

        System.out.println("Size after adding: " + twoWayLinkedList.size());

        System.out.println("index of \"E\": " + twoWayLinkedList.indexOf("E") + "\n\nafter operations:");


        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }

        System.out.println("Empty spaces before defragment: " + twoWayLinkedList.capacity());
        System.out.println("Endcapacity before defragment: " + twoWayLinkedList.endCapacity());
        twoWayLinkedList.defragment();
        System.out.println("Empty spaces after defragment: " + twoWayLinkedList.capacity());

        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }

        twoWayLinkedList.clear();
        System.out.println("If the list is empty after clearing: " + twoWayLinkedList.isEmpty());

        System.out.println("\n\n\nCELLS PER NODE ARE EVEN");

        twoWayLinkedList = new TwoWayLinkedList<>(4);
        twoWayLinkedList.add("A");
        twoWayLinkedList.add("B");
        twoWayLinkedList.add("C");
        twoWayLinkedList.add("D");
        twoWayLinkedList.add("E");
        twoWayLinkedList.add("F");
        twoWayLinkedList.add("G");
        twoWayLinkedList.add("H");
        twoWayLinkedList.add("I");
        twoWayLinkedList.add("J");
        twoWayLinkedList.add("K");
        twoWayLinkedList.add("L");


        System.out.println("Removed: \n" + twoWayLinkedList.remove("D"));
        System.out.println(twoWayLinkedList.remove("A"));
        System.out.println(twoWayLinkedList.remove("B"));
        System.out.println(twoWayLinkedList.remove("C"));
        System.out.println("Modification: " + twoWayLinkedList.set(7, "TESTER") + " turned to " + twoWayLinkedList.get(7));
        System.out.println("Get value on index 2: " + twoWayLinkedList.get(2));
        System.out.println("Size before adding: " + twoWayLinkedList.size());

        twoWayLinkedList.add(0, "A1");
        twoWayLinkedList.add(4, "A2");
        twoWayLinkedList.add(4, "A3");
        twoWayLinkedList.add(4, "A4");
        twoWayLinkedList.add(4, "A5");
        twoWayLinkedList.add(4, "A6");
        twoWayLinkedList.add(4, "A7");

        twoWayLinkedList.add(twoWayLinkedList.size() - 1, "X");
        twoWayLinkedList.add(16, "Y");

        System.out.println("Size after adding: " + twoWayLinkedList.size());

        System.out.println("index of \"E\": " + twoWayLinkedList.indexOf("E") + "\n\nafter operations:");


        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }

        twoWayLinkedList.clear();
        System.out.println("If the list is empty after clearing: " + twoWayLinkedList.isEmpty());


        System.out.println("\n\n\nCELLS PER NODE IS 1");

        twoWayLinkedList = new TwoWayLinkedList<>(1);
        twoWayLinkedList.add("A");
        twoWayLinkedList.add("B");
        twoWayLinkedList.add("C");
        twoWayLinkedList.add("D");
        twoWayLinkedList.add("E");
        twoWayLinkedList.add("F");
        twoWayLinkedList.add("G");
        twoWayLinkedList.add("H");
        twoWayLinkedList.add("I");
        twoWayLinkedList.add("J");
        twoWayLinkedList.add("K");
        twoWayLinkedList.add("L");


        System.out.println("Removed: \n" + twoWayLinkedList.remove("D"));
        System.out.println(twoWayLinkedList.remove("A"));
        System.out.println(twoWayLinkedList.remove("B"));
        System.out.println(twoWayLinkedList.remove("C"));
        System.out.println("Modification: " + twoWayLinkedList.set(7, "TESTER") + " turned to " + twoWayLinkedList.get(7));
        System.out.println("Get value on index 2: " + twoWayLinkedList.get(2));
        System.out.println("Size before adding: " + twoWayLinkedList.size());

        twoWayLinkedList.add(0, "A1");
        twoWayLinkedList.add(4, "A2");
        twoWayLinkedList.add(4, "A3");
        twoWayLinkedList.add(4, "A4");
        twoWayLinkedList.add(4, "A5");
        twoWayLinkedList.add(4, "A6");
        twoWayLinkedList.add(4, "A7");

        twoWayLinkedList.add(twoWayLinkedList.size() - 1, "X");
        twoWayLinkedList.add(twoWayLinkedList.size() - 1, "Y");
        twoWayLinkedList.add(twoWayLinkedList.size() - 1, "Z");

        System.out.println("Size after adding: " + twoWayLinkedList.size());

        System.out.println("index of \"E\": " + twoWayLinkedList.indexOf("E") + "\n\nafter operations:");


        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }

        twoWayLinkedList.clear();
        System.out.println("If the list is empty after clearing: " + twoWayLinkedList.isEmpty());

        System.out.println("\n\n\nJUST ONE NODE (BIG ARRAY SIZE)");

        twoWayLinkedList = new TwoWayLinkedList<>(20);
        twoWayLinkedList.add("A");
        twoWayLinkedList.add("B");
        twoWayLinkedList.add("C");
        twoWayLinkedList.add("D");
        twoWayLinkedList.add("E");
        twoWayLinkedList.add("F");
        twoWayLinkedList.add("G");
        twoWayLinkedList.add("H");
        twoWayLinkedList.add("I");
        twoWayLinkedList.add("J");
        twoWayLinkedList.add("K");
        twoWayLinkedList.add("L");


        System.out.println("Removed: \n" + twoWayLinkedList.remove("D"));
        System.out.println(twoWayLinkedList.remove("A"));
        System.out.println(twoWayLinkedList.remove("B"));
        System.out.println(twoWayLinkedList.remove("C"));
        System.out.println("Modification: " + twoWayLinkedList.set(7, "TESTER") + " turned to " + twoWayLinkedList.get(7));
        System.out.println("Get value on index 2: " + twoWayLinkedList.get(2));
        System.out.println("Size before adding: " + twoWayLinkedList.size());

        twoWayLinkedList.add(0, "A1");
        twoWayLinkedList.add(4, "A2");
        twoWayLinkedList.add(4, "A3");
        twoWayLinkedList.add(4, "A4");
        twoWayLinkedList.add(4, "A5");
        twoWayLinkedList.add(4, "A6");
        twoWayLinkedList.add(4, "A7");

        twoWayLinkedList.add(15, "X");
        twoWayLinkedList.add(16, "Y");
        twoWayLinkedList.add(17, "Z");

        System.out.println("Size after adding: " + twoWayLinkedList.size());

        System.out.println("index of \"E\": " + twoWayLinkedList.indexOf("E") + "\n\nafter operations:");


        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }

        twoWayLinkedList.clear();
        System.out.println("If the list is empty after clearing: " + twoWayLinkedList.isEmpty());

        try {
            twoWayLinkedList.remove(7);
        }catch (Exception e){
        }
        try{twoWayLinkedList.add(3,"3");} catch(Exception e){}
        twoWayLinkedList.add("TEST");

        for (String s : twoWayLinkedList) {
            System.out.println(s);
        }
    }

}
