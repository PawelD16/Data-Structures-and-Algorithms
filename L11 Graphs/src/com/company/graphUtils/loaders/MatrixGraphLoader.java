package com.company.graphUtils.loaders;

import com.company.graphUtils.MalformedGraphDescriptionException;
import com.company.graphs.AdjacencyMatrixWeightedDigraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatrixGraphLoader extends Loader {

    public static AdjacencyMatrixWeightedDigraph loadMatrixGraph(String path) throws MalformedGraphDescriptionException {
        int u = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String string;
            if ((string = br.readLine()) != null) {
                u = Integer.parseInt(string);
            }
        } catch (FileNotFoundException e) {
            throw new MalformedGraphDescriptionException("There is no file!");
        } catch (IOException e) {
            throw new MalformedGraphDescriptionException("There was a problem with reading the file!");
        }

        return (AdjacencyMatrixWeightedDigraph) Loader.loadGraph(path, new AdjacencyMatrixWeightedDigraph(u));
    }

    /*
    @Override
    public AdjacencyMatrixWeightedDigraph loadGraph(String path) throws MalformedGraphDescriptionException {
        return (AdjacencyMatrixWeightedDigraph) super.loadGraph(path);
    }

     */
}
