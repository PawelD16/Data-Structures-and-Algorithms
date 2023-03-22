package com.company.minimalSpanningTree;

import com.company.graphUtils.Converter;
import com.company.graphs.IWeightedDigraph;

import java.util.List;

public abstract class MinimumSpanningTreeAlgorithm<T extends IWeightedDigraph> {

    protected List<Converter.WeightedEdge> edges;

    public abstract T minimise();
}
