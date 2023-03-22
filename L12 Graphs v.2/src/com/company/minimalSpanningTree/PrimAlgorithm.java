package com.company.minimalSpanningTree;

import com.company.graphUtils.Converter;
import com.company.graphs.AdjacencyListWeightedDigraph;
import com.company.graphs.AdjacencyMatrixWeightedDigraph;
import com.company.graphs.IWeightedDigraph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimAlgorithm<T extends IWeightedDigraph> extends MinimumSpanningTreeAlgorithm<T> {

    private final ArrayList<Integer> unconnectedVertices;
    private final ArrayList<Integer> connectedVertices;
    T newDigraph, oldDigraph;
    PriorityQueue<Converter.WeightedEdge> edgesQueue;

    @SuppressWarnings("unchecked")
    public PrimAlgorithm(T diGraph, int startingVertex) {
        if (!diGraph.containsVertex(startingVertex)) throw new IllegalArgumentException("Given vertex does not exist!");
        edges = diGraph.getAllPairs();
        unconnectedVertices = new ArrayList<>();
        connectedVertices = new ArrayList<>();

        edgesQueue = new PriorityQueue<>(Comparator.comparingDouble(Converter.WeightedEdge::getWeight));

        for (Converter.WeightedEdge edge : edges) {
            if (!unconnectedVertices.contains(edge.getBeginning())) unconnectedVertices.add(edge.getBeginning());
            if (!unconnectedVertices.contains(edge.getEnd())) unconnectedVertices.add(edge.getEnd());
        }

        if (diGraph instanceof AdjacencyMatrixWeightedDigraph) newDigraph = (T) new AdjacencyMatrixWeightedDigraph();
        else newDigraph = (T) new AdjacencyListWeightedDigraph();

        this.oldDigraph = diGraph;

        connectVertex(startingVertex);
    }

    @Override
    public T minimise() {
        while (unconnectedVertices.size() != connectedVertices.size()) {
            Converter.WeightedEdge tempEdge = edgesQueue.poll();
            if (connectVertex(tempEdge.getEnd()))
                newDigraph.addEdgeU(tempEdge.getBeginning(), tempEdge.getEnd(), tempEdge.getWeight());
        }

        return newDigraph;
    }

    private boolean connectVertex(int vertex) {
        if (connectedVertices.contains(vertex)) return false;
        connectedVertices.add(vertex);

        edges = oldDigraph.verticesConnectedToAsWeightedEdges(vertex);
        for (Converter.WeightedEdge edge : edges) if (!connectedVertices.contains(edge.getEnd())) edgesQueue.add(edge);

        return true;
    }
}
