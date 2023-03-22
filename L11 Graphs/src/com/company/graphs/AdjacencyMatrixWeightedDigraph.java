package com.company.graphs;

import com.company.graphUtils.Converter;

import java.util.*;

public class AdjacencyMatrixWeightedDigraph implements IWeightedDigraph {

    private Double[][] matrix; //uzywam Double (nie double), aby moc wpisywac wartosc null (sposob na umożliwienie wpisania wartosci ujemnych, dodatnich oraz 0 jako wagi krawedzi)

    public AdjacencyMatrixWeightedDigraph(int n) {
        matrix = new Double[n][n];
        fillMatrixWithInfinities(matrix);
    }

    public AdjacencyMatrixWeightedDigraph() {
        matrix = new Double[10][10];
        fillMatrixWithInfinities(matrix);
    }

    @Override
    public int edgeCount() {
        int counter = 0;
        for (Double[] doubles : matrix) {
            for (Double aDouble : doubles) {
                if (aDouble != Double.POSITIVE_INFINITY) counter++;
            }
        }
        return counter;
    }

    @Override
    public int vertexCount() {
        ArrayList<Integer> vertices = new ArrayList<>();

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] != Double.POSITIVE_INFINITY) {
                    if (!vertices.contains(i)) vertices.add(i);
                    if (!vertices.contains(j)) vertices.add(j);
                }
            }
        }

        return vertices.size();
    }

    @Override
    public boolean addEdge(int u, int v, double w) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();
        int temp = Math.max(u, v);
        if (temp >= matrix.length) { //liczymy krawedzie od 0, wiec >=. Wystarczy sprawdzic dla 1 krawedzi, bo ta macierz jest kwadratowa
            ++temp;
            Double[][] newMatrix = new Double[temp][temp];
            //fillMatrixWithInfinities(newMatrix);
            copySmallerToBiggerMatrixAndFIll(newMatrix, matrix);
            matrix = newMatrix;
        }

        return addElementToMatrix(u, v, w);
    }

    @Override
    public boolean addEdgeU(int u, int v, double w) { //nie nadpisujemy pojedynczych krawedzi
        if (!hasEdge(u, v) && !hasEdge(v, u)) {
            addEdge(u, v, w);
            addEdge(v, u, w);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEdge(int u, int v) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        int temp = Math.max(u, v);
        if (temp >= matrix.length) return false;
        else if (matrix[u][v] == Double.POSITIVE_INFINITY) return false;

        matrix[u][v] = Double.POSITIVE_INFINITY;
        return true;
    }

    @Override
    public boolean removeEdgeU(int u, int v) {
        return removeEdge(u, v) | removeEdge(v, u); //wystarczy ze usuiniemy jeden, jezeli nie ma takiego wezla/krawedzi z obu stron to dostaniemy 2 razy false czyli false
    }

    @Override
    public boolean hasEdge(int u, int v) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        return Math.max(u, v) < matrix.length && matrix[u][v] != Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean hasEdgeU(int u, int v) {
        return hasEdge(u, v) && hasEdge(v, u);
    }

    @Override
    public List<Integer> verticesConnectedTo(int v) {
        if (v < 0) throw new IllegalArgumentException();
        List<Integer> listOfConnectedNodes = new ArrayList<>();
        if (v >= matrix.length) return listOfConnectedNodes; //zwracamy pusta liste jezeli nie istnieje dany wezel

        for (int i = 0; i < matrix.length; ++i) {
            if (matrix[i][v] != Double.POSITIVE_INFINITY) listOfConnectedNodes.add(i);
        }

        return listOfConnectedNodes;
    }

    @Override
    public double weight(int u, int v) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();
        if (Math.max(u, v) >= matrix.length) return Double.POSITIVE_INFINITY;
        else return matrix[u][v];
    }

    @Override
    public void weight(int u, int v, double w) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();
        if (Math.max(u, v) < matrix.length) {
            matrix[u][v] = w;
        }
    }

    @Override
    public Iterator<Converter.WeightedEdge> edges(int v) {
        if (v < 0 || v > matrix.length) throw new NullPointerException();

        return new Iterator<Converter.WeightedEdge>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                while (pos < matrix[v].length && matrix[v][pos] == Double.POSITIVE_INFINITY) pos++;
                return pos < matrix[v].length;
            }

            @Override
            public Converter.WeightedEdge next() {
                if (hasNext()) {
                    pos++;
                    return new Converter.WeightedEdge(v, pos - 1, matrix[v][pos - 1]);
                } else throw new NoSuchElementException();
            }
        };
    }

    @Override
    public List<Converter.WeightedEdge> getAllPairs() {
        ArrayList<Converter.WeightedEdge> weightedEdges = new ArrayList<>(matrix.length * matrix.length);

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] != Double.POSITIVE_INFINITY)
                    weightedEdges.add(new Converter.WeightedEdge(i, j, matrix[i][j]));
            }
        }
        return weightedEdges;
    }

    private void fillMatrixWithInfinities(Double[][] aMatrix) {
        for (Double[] doubles : aMatrix) Arrays.fill(doubles, Double.POSITIVE_INFINITY);
    }

    private void copySmallerToBiggerMatrixAndFIll(Double[][] aMatrix, Double[][] bMatrix) { //bMatrix jest mniejszy
        for (int i = 0; i < aMatrix.length; ++i)
            for (int j = 0; j < aMatrix[i].length; ++j) {
                if (i < bMatrix.length && j < bMatrix[i].length) aMatrix[i][j] = bMatrix[i][j];
                else aMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
    }

    private boolean addElementToMatrix(int u, int v, double w) { //jestemy pewni, ze sie zmiesci
        if (matrix[u][v] == Double.POSITIVE_INFINITY) {
            matrix[u][v] = w;
            return true;
        } else return false;
    }
}
