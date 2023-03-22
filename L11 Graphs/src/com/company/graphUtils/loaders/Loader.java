package com.company.graphUtils.loaders;

import com.company.graphUtils.MalformedGraphDescriptionException;
import com.company.graphs.IWeightedDigraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

abstract class Loader {
    protected static IWeightedDigraph loadGraph(String path, IWeightedDigraph weightedDigraph) throws MalformedGraphDescriptionException {

        int i = 0;  //licznik do linii
        int u, v;
        double w;
        String string;  //kubełek na literke "u" i tokeny

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            if ((string = br.readLine()) != null) {
                Integer.parseInt(string); //sprawdzamy czy to byla liczba calkowita
            }

            while ((string = br.readLine()) != null) {
                ++i;
                String[] tokens = string.split(" ");

                if (tokens.length < 3 || tokens.length > 4)
                    throw new MalformedGraphDescriptionException("Line: " + i + ", read token has incorrect amount of arguments");

                if (tokens.length == 4) {
                    string = tokens[0];
                    if (!string.equals("u"))
                        throw new MalformedGraphDescriptionException("Line: " + i + ", expected letter 'u' or nothing, but was given " + string);
                }

                u = Integer.parseInt(tokens[tokens.length - 3]);
                if (u < 0)
                    throw new MalformedGraphDescriptionException("Line:" + i + ", expected positive integer as 'u', but was given " + u);

                v = Integer.parseInt(tokens[tokens.length - 2]);
                if (v < 0)
                    throw new MalformedGraphDescriptionException("Line:" + i + ", expected positive integer as 'v', but was given " + v);

                w = Double.parseDouble(tokens[tokens.length - 1]);

                if (tokens.length == 4) {
                    if (!weightedDigraph.addEdgeU(u, v, w))
                        throw new MalformedGraphDescriptionException("Line: " + i + ", this undirected edge already exists or there is a directed edge in its place");
                } else {
                    if (!weightedDigraph.addEdge(u, v, w))
                        throw new MalformedGraphDescriptionException("Line: " + i + ", this edge already exists!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new MalformedGraphDescriptionException("There is no file!");
        } catch (IOException e) {
            throw new MalformedGraphDescriptionException("There was a problem with reading the file!");
        } catch (NumberFormatException e) {
            throw new MalformedGraphDescriptionException("Line: " + i + ", a number was of incorrect format!");
        }

        return weightedDigraph;
    }
}
