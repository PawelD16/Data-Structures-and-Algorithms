package com.company;

public class Main {

    public static void main(String[] args) {

        IterFib iterFib = new IterFib(250);
        for (int i = 0; i < 4; i++) {
            System.out.println(iterFib.current());
            iterFib.next();
        }
        System.out.println(iterFib.current());

        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.println(iterFib.current());
            iterFib.previous();
        }
        System.out.println(iterFib.current());
    }
}
