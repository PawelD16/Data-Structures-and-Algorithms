package com.company.minimalSpanningTree;

import com.company.graphUtils.Converter;
import com.company.graphs.AdjacencyListWeightedDigraph;
import com.company.graphs.AdjacencyMatrixWeightedDigraph;
import com.company.graphs.IWeightedDigraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm<T extends IWeightedDigraph> extends MinimumSpanningTreeAlgorithm<T> {
    private final ArrayList<T> trees;

    @SuppressWarnings("unchecked")
    public KruskalAlgorithm(T diGraph) {

        edges = diGraph.getAllPairs();
        edges.sort(Comparator.comparingDouble(Converter.WeightedEdge::getWeight));
        int edgesSize = edges.size();
        trees = new ArrayList<>(edgesSize);

        for (int i = 0; i < edgesSize; ++i) {
            T newT;
            if (diGraph instanceof AdjacencyMatrixWeightedDigraph) newT = (T) new AdjacencyMatrixWeightedDigraph();
            else newT = (T) new AdjacencyListWeightedDigraph();
            trees.add(newT);
        }
    }


    @Override
    public T minimise() {
        while (edges.size() > 0) {
            Converter.WeightedEdge edge = edges.remove(0);

            T beginningTree = null, endTree = null;
            for (T tree : trees) {
                if (tree.containsVertex(edge.getBeginning())) beginningTree = tree;
                if (tree.containsVertex(edge.getEnd())) endTree = tree;
            }

            if (beginningTree == null && endTree == null) {
                trees.get(edge.getBeginning()).addEdgeU(edge.getBeginning(), edge.getEnd(), edge.getWeight());
                trees.remove(edge.getEnd());
            }


            if (beginningTree != endTree) {
                if (beginningTree == null) {
                    beginningTree = endTree;
                    endTree = null;
                    trees.remove(edge.getBeginning());
                } else trees.remove(edge.getEnd());

                List<Converter.WeightedEdge> secondEdges;

                if (endTree != null) {
                    secondEdges = endTree.getAllPairs();
                    for (Converter.WeightedEdge edgeToUnion : secondEdges) {
                        beginningTree.addEdgeU(edgeToUnion.getBeginning(), edgeToUnion.getEnd(), edgeToUnion.getWeight());
                    }
                    trees.remove(endTree);

                }
                beginningTree.addEdgeU(edge.getBeginning(), edge.getEnd(), edge.getWeight());
            }
        }

        trees.sort(Comparator.comparingInt(IWeightedDigraph::edgeCount).reversed());
        return trees.get(0);
    }
}
