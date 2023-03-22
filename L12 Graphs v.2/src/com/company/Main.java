package com.company;


import com.company.graphUtils.*;
import com.company.graphUtils.loaders.*;
import com.company.graphs.*;
import com.company.minimalSpanningTree.KruskalAlgorithm;
import com.company.minimalSpanningTree.PrimAlgorithm;

public class Main {

    public static void main(String[] args) {

        //new Tester<>(new AdjacencyListWeightedDigraph());
        //new Tester<>(new AdjacencyMatrixWeightedDigraph());

        AdjacencyListWeightedDigraph listWeightedDigraph = new AdjacencyListWeightedDigraph();

        try {
            listWeightedDigraph = ListGraphLoader.loadListGraph("./primFromLecture.txt");
        } catch (MalformedGraphDescriptionException e) {
            e.printStackTrace();
        }

/*
        AdjacencyMatrixWeightedDigraph matrixWeightedDigraph = new AdjacencyMatrixWeightedDigraph();
        try {
            matrixWeightedDigraph = MatrixGraphLoader.loadMatrixGraph("./primFromLecture.txt");
        } catch (MalformedGraphDescriptionException e) {
            e.printStackTrace();
        }

 */

        //listWeightedDigraph = new Converter().convert(matrixWeightedDigraph);
        //new PrimAlgorithm<>(listWeightedDigraph, 1);

        listWeightedDigraph = new PrimAlgorithm<>(listWeightedDigraph, 1).minimise();

        System.out.println("test");

        int n = 1000, x = 4;
        AdjacencyListWeightedDigraph list = Tester12.generateVertices(new AdjacencyListWeightedDigraph(), n, x);
        System.out.println("LIST:");
        Tester12.measureAndMinimise(new PrimAlgorithm<>(list, 1));

        AdjacencyListWeightedDigraph list2 = Tester12.generateVertices(new AdjacencyListWeightedDigraph(), n, x);
        Tester12.measureAndMinimise(new KruskalAlgorithm<>(list2));

        AdjacencyMatrixWeightedDigraph matrix = Tester12.generateVertices(new AdjacencyMatrixWeightedDigraph(n), n, x);
        System.out.println("\n\nMATRIX:");
        Tester12.measureAndMinimise(new PrimAlgorithm<>(matrix, 1));

        AdjacencyMatrixWeightedDigraph matrix2 = Tester12.generateVertices(new AdjacencyMatrixWeightedDigraph(n), n, x);
        Tester12.measureAndMinimise(new KruskalAlgorithm<>(matrix2));


    }
}
