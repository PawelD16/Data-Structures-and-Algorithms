package com.company.graphUtils.loaders;

import com.company.graphUtils.MalformedGraphDescriptionException;
import com.company.graphs.AdjacencyListWeightedDigraph;

public class ListGraphLoader extends Loader {

    public static AdjacencyListWeightedDigraph loadListGraph(String path) throws MalformedGraphDescriptionException {
        return (AdjacencyListWeightedDigraph) Loader.loadGraph(path, new AdjacencyListWeightedDigraph());
    }
}
