package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class TesterForListDisjointSet {

    public TesterForListDisjointSet(int n) {
        ArrayList<ListDisjointSet> listDisjointSets = new ArrayList<>(n);
        ListDisjointSet[] referencesToAllNodes = new ListDisjointSet[n];
        for (int i = 0; i < n; ++i) {
            listDisjointSets.add(new ListDisjointSet().makeSet());
            referencesToAllNodes[i] = listDisjointSets.get(i);
        }

        Instant startUnionTime = Instant.now();
        while (listDisjointSets.size() > 1) {
            for (int i = 0; i < listDisjointSets.size() - 1; ++i) {
                listDisjointSets.get(i).union(listDisjointSets.remove(i + 1));//nasze zbiory maja te sama wielkosc, wiec wiemy, ze zosatnnie on zapisany w i
            }
        }
        Instant stopUnionTime = Instant.now();
        double timeOfUnion = Duration.between(startUnionTime, stopUnionTime).toMillis();

        Instant startFindSetTime = Instant.now();
        for (int i = n - 1; i >= 0; --i) {
            referencesToAllNodes[i] = referencesToAllNodes[i].findSet();
        }
        Instant stopFindSetTime = Instant.now();
        double timeOfFindSet = Duration.between(startFindSetTime, stopFindSetTime).toMillis();

        System.out.println("\nType: ListDisjointSet"  + "\nUnionTime: " + timeOfUnion + "\nFindSetTime: " + timeOfFindSet);
    }
}
