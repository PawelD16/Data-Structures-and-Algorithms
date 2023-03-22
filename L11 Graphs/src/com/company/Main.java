package com.company;


import com.company.graphUtils.*;
import com.company.graphUtils.loaders.*;
import com.company.graphs.*;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        //new Tester<>(new AdjacencyListWeightedDigraph());
        //new Tester<>(new AdjacencyMatrixWeightedDigraph());


        AdjacencyListWeightedDigraph listWeightedDigraph = new AdjacencyListWeightedDigraph();

        try {
            listWeightedDigraph = ListGraphLoader.loadListGraph("./abcd.txt");
        } catch (MalformedGraphDescriptionException e) {
            e.printStackTrace();
        }


        AdjacencyMatrixWeightedDigraph matrixWeightedDigraph = new AdjacencyMatrixWeightedDigraph();
        try {
            matrixWeightedDigraph = MatrixGraphLoader.loadMatrixGraph("./abcd.txt");
        } catch (MalformedGraphDescriptionException e) {
            e.printStackTrace();
        }

        listWeightedDigraph = new Converter().convert(matrixWeightedDigraph);

        Iterator<Converter.WeightedEdge> iterator = matrixWeightedDigraph.edges(3);

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //AdjacencyListWeightedDigraph listWeightedDigraph = new Converter().convert(matrixWeightedDigraph);
        listWeightedDigraph.weight(0, 0);

    }
}
