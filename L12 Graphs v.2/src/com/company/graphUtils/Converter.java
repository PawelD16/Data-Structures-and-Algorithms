package com.company.graphUtils;

import com.company.graphs.AdjacencyListWeightedDigraph;
import com.company.graphs.AdjacencyMatrixWeightedDigraph;

import java.util.List;

public class Converter {

    public AdjacencyListWeightedDigraph convert(AdjacencyMatrixWeightedDigraph matrixWeightedDigraph) {
        List<WeightedEdge> weightedEdges = matrixWeightedDigraph.getAllPairs();
        AdjacencyListWeightedDigraph listWeightedDigraph = new AdjacencyListWeightedDigraph();

        for (WeightedEdge weightedEdge : weightedEdges) {
            listWeightedDigraph.addEdge(weightedEdge.beginning, weightedEdge.end, weightedEdge.weight);
        }

        return listWeightedDigraph;
    }

    public AdjacencyMatrixWeightedDigraph convert(AdjacencyListWeightedDigraph listWeightedDigraph) {
        List<WeightedEdge> weightedEdges = listWeightedDigraph.getAllPairs();
        AdjacencyMatrixWeightedDigraph matrixWeightedDigraph = new AdjacencyMatrixWeightedDigraph();

        for (WeightedEdge weightedEdge : weightedEdges) {
            matrixWeightedDigraph.addEdge(weightedEdge.beginning, weightedEdge.end, weightedEdge.weight);
        }

        return matrixWeightedDigraph;
    }

    public static class WeightedEdge {
        private final int beginning;
        private final int end;
        private final double weight;

        public WeightedEdge(int beginning, int end, double weight) {
            this.beginning = beginning;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[" + this.beginning + "," + this.end + "]";
        }

        public int getBeginning() {
            return beginning;
        }

        public int getEnd() {
            return end;
        }

        public double getWeight() {
            return weight;
        }
    }
}
