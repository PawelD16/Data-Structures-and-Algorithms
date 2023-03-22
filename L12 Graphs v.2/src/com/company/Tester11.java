package com.company;

import com.company.graphs.IWeightedDigraph;

public class Tester11<T extends IWeightedDigraph> {

    public Tester11(T toBeTested) {

        System.out.println("Type: " + toBeTested.getClass().getSimpleName());

        System.out.println("AddingU 4,5,3.08: " + toBeTested.addEdgeU(4, 5, 3.08));
        System.out.println("Adding 1, 2, 21.0: " + toBeTested.addEdge(1, 2, 21.0));
        System.out.println("AddingU 1, 2, 1.0: " + toBeTested.addEdgeU(1, 2, 1.0) + " (should be false)");
        System.out.println("Adding 6, 6, 0.2: " + toBeTested.addEdge(6, 6, 0.2));
        System.out.println("AddingU 7, 7, 0.4: " + toBeTested.addEdgeU(7, 7, 0.4));
        System.out.println("hasEdgeU 4, 5: " + toBeTested.hasEdgeU(4, 5));
        System.out.println("hasEdge 1, 2: " + toBeTested.hasEdge(1, 2));
        System.out.println("Weight of 1,2: " + toBeTested.weight(1, 2));
        toBeTested.weight(1, 2, 6.0);
        System.out.println("Weight of 1,2 after setting to 6.0: " + toBeTested.weight(1, 2));
        System.out.println("EdgeCount: " + toBeTested.edgeCount() + " (should be 5)");
        System.out.println("Vertex count: " + toBeTested.vertexCount() + " (should be 6)");
        System.out.println("Removing 1,2: " + toBeTested.removeEdge(1, 2));
        System.out.println("RemovingU 4,5: " + toBeTested.removeEdgeU(4, 5));
        System.out.println("Removing non existent 3, 7: " + toBeTested.removeEdge(3, 7));
        System.out.println("EdgeCount: " + toBeTested.edgeCount() + " (should be 2)");
        System.out.println("Vertex count: " + toBeTested.vertexCount() + " (should be 2)");
        System.out.println();
    }
}
