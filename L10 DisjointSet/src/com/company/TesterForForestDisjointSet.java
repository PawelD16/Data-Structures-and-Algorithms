package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class TesterForForestDisjointSet {
    public TesterForForestDisjointSet(int n) {
        ArrayList<ForestDisjointSet> forestDisjointSets = new ArrayList<>(n);
        ForestDisjointSet[] referencesToAllNodes = new ForestDisjointSet[n];
        for (int i = 0; i < n; ++i) {
            forestDisjointSets.add(new ForestDisjointSet().makeSet());
            referencesToAllNodes[i] = forestDisjointSets.get(i);
        }

        Instant startUnionTime = Instant.now();
        while (forestDisjointSets.size() > 1) {
            for (int i = 0; i < forestDisjointSets.size() - 1; ++i) {
                forestDisjointSets.get(i).union(forestDisjointSets.remove(i + 1));//nasze zbiory maja te sama wielkosc, wiec wiemy, ze zosatnnie on zapisany w i
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

        System.out.println("\nType: ListDisjointSet" + "\nUnionTime: " + timeOfUnion + "\nFindSetTime: " + timeOfFindSet);

    }
}
