package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Test {

    public Test(int n) {
        double x = Math.ceil(Math.sqrt(Math.sqrt(n)));

        HappyBinarySearchTree<Integer> HBST = new HappyBinarySearchTree<>(Integer::compareTo);
        BinarySearchTree<Integer> BST = new BinarySearchTree<>(Integer::compareTo);

        ArrayList<Integer> listHBST = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listHBST.add(i);
        }

        Collections.shuffle(listHBST);
        ArrayList<Integer> listBST = new ArrayList<>(listHBST);


        Instant startAddHBST = Instant.now();
        for (Integer integer : listHBST) {
            HBST.add(integer);
        }
        Instant endAddHBST = Instant.now();
        double HBSTAddTime = Duration.between(startAddHBST, endAddHBST).toMillis();

        Instant startFindHBST = Instant.now();
        for (int i = 0; i < Math.ceil((double) n / x); i++) {
            HBST.find(listHBST.get(new Random().nextInt(listHBST.size())));
        }
        Instant endFindHBST = Instant.now();
        double HBSTFindTime = Duration.between(startFindHBST, endFindHBST).toMillis();

        Instant startDeleteHBST = Instant.now();
        for (int i = 0; i < Math.ceil((double) n / x); i++) {
            HBST.delete(listHBST.get(new Random().nextInt(listHBST.size())));
        }
        Instant endDeleteHBST = Instant.now();
        double HBSTDeleteTime = Duration.between(startDeleteHBST, endDeleteHBST).toMillis();


        Instant startAddBST = Instant.now();
        for (Integer integer : listBST) {
            BST.add(integer);
        }
        Instant endAddBST = Instant.now();
        double BSTAddTime = Duration.between(startAddBST, endAddBST).toMillis();

        Instant startFindBST = Instant.now();
        for (int i = 0; i < Math.ceil((double) n / x); i++) {
            BST.find(listBST.get(new Random().nextInt(listBST.size())));
        }
        Instant endFindBST = Instant.now();
        double BSTFindTime = Duration.between(startFindBST, endFindBST).toMillis();

        Instant startDeleteBST = Instant.now();
        for (int i = 0; i < Math.ceil((double) n / x); i++) {
            BST.delete(listBST.get(new Random().nextInt(listBST.size())));
        }
        Instant endDeleteBST = Instant.now();
        double BSTDeleteTime = Duration.between(startDeleteBST, endDeleteBST).toMillis();

        System.out.println("HBST:\nAdd time: " + HBSTAddTime + "\nFind time: " + HBSTFindTime + "\nDelete time: " + HBSTDeleteTime);

        System.out.println("BST:\nAdd time: " + BSTAddTime + "\nFind time: " + BSTFindTime + "\nDelete time: " + BSTDeleteTime);
    }
}
