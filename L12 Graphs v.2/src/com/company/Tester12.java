package com.company;

import com.company.graphs.IWeightedDigraph;
import com.company.minimalSpanningTree.MinimumSpanningTreeAlgorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public abstract class Tester12 {

    public static <T extends MinimumSpanningTreeAlgorithm<IWeightedDigraph>> void measureAndMinimise(T mstAlgorithm) {

        Instant startMST = Instant.now();
        mstAlgorithm.minimise();
        Instant stopMST = Instant.now();

        double timeElapsed = Duration.between(startMST, stopMST).toMillis();

        System.out.println("\nType: " + mstAlgorithm.getClass().getSimpleName() + "\nTime: " + timeElapsed);
    }

    public static <T extends IWeightedDigraph> T generateVertices(T diGraph, int amountOfVertices, int edgesPerVertex) {
        for (int j = 0; j < edgesPerVertex; j++) {
            for (int i = 0; i < amountOfVertices; ++i) {
                diGraph.addEdgeU(i, getRandomInteger(i, amountOfVertices), getRandomDouble(amountOfVertices * edgesPerVertex));
            }
        }      //nie gwarantuje to poprawnego grafu ( moze nie byc sposobu na dojscie do wezla), ale na potrzeby testowania takie zalozenie wystarczy
        return diGraph;
    }

    private static int getRandomInteger(int number, int max) {
        Random generator = new Random();
        int i = number;
        while (i == number) {
            i = generator.nextInt(max);
        }
        return i;
    }

    private static double getRandomDouble(double max) {
        return new Random().nextDouble() * max;
    }

}
