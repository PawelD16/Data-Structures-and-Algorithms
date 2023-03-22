package com.company.graphs;

import com.company.graphUtils.Converter;

import java.util.*;

public class AdjacencyListWeightedDigraph implements IWeightedDigraph {
    private final LinkedList<Vertex> vertices;

    public AdjacencyListWeightedDigraph() {
        vertices = new LinkedList<>();
    }

    @Override
    public int edgeCount() {
        int counter = 0;
        for (Vertex vertex : vertices) {
            counter += vertex.edges.size();
        }
        return counter;
    }

    @Override
    public int vertexCount() {
        return vertices.size();
    }

    @Override
    public boolean addEdge(int u, int v, double w) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        Vertex uVertex = orderedAddVertex(u);   //zwracamy referencje na wezel, aby moc dodac mu element
        boolean isAdded = orderedAddEdge(uVertex, v, w);
        if (!isAdded) return false;
        orderedAddVertex(v);    //tworzymy ewentualnie wezel v (wezly, nawet te z którymi tylko sie cos laczy, musza sie znajdowac na liscie). To sie nie wykonuje, jezeli nie dodajemy wezla
        return true;
    }

    @Override
    public boolean addEdgeU(int u, int v, double w) {   //nie nadpisujemy pojedynczych krawedzi
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

        List<Integer> connectedToV = verticesConnectedTo(v);
        Vertex vVertex = findVertex(v);

        List<Integer> connectedToU = verticesConnectedTo(u);
        Vertex uVertex = findVertex(u);

        if (uVertex == null || vVertex == null) return false;

        if (connectedToV.size() == 1 && vVertex.edges.size() == 0) vertices.remove(vVertex);    //usuwanie v

        if (connectedToU.size() == 0 && uVertex.edges.size() == 1) vertices.remove(uVertex);    //usuwanie u
        else {
            for (Edge edge : uVertex.edges) {
                if (edge.numberOfSecondVertex == v) {
                    uVertex.edges.remove(edge);
                    return true;
                }
                if (edge.numberOfSecondVertex > v) return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeEdgeU(int u, int v) {
        return removeEdge(u, v) | removeEdge(v, u); //chcemy wykonac oba wiec | nie ||
    }

    @Override
    public boolean hasEdge(int u, int v) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        for (Vertex vertex : vertices) {
            if (vertex.numberOfFirstVertex == u) {
                for (Edge edge : vertex.edges) {
                    if (edge.numberOfSecondVertex == v) return true;
                    if (edge.numberOfSecondVertex > v) return false;
                }
            }
            if (vertex.numberOfFirstVertex > u) return false;   //poukladany rosnąco, wiec mozemy dodac taki warunek
        }
        return false;
    }

    @Override
    public boolean hasEdgeU(int u, int v) {
        return hasEdge(u, v) && hasEdge(v, u);
    }

    @Override
    public List<Integer> verticesConnectedTo(int v) {

        if (v < 0) throw new IllegalArgumentException();

        List<Integer> list = new ArrayList<>();
        for (Vertex vertex : vertices) {
            if (vertex.numberOfFirstVertex == v) {
                for (Edge edge : vertex.edges) {
                    list.add(edge.numberOfSecondVertex);
                }
            }
            if (vertex.numberOfFirstVertex > v) break;
        }
        return list;
        /*
        if (v < 0) throw new IllegalArgumentException();

        List<Integer> listOfConnectedNodes = new ArrayList<>();
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.edges) {
                if (edge.numberOfSecondVertex == v) listOfConnectedNodes.add(edge.numberOfSecondVertex);
                if (edge.numberOfSecondVertex > v) break;
            }
        }
        return listOfConnectedNodes;

         */
    }

    @Override
    public double weight(int u, int v) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        for (Vertex vertex : vertices) {
            if (vertex.numberOfFirstVertex == u) {
                for (Edge edge : vertex.edges) {
                    if (edge.numberOfSecondVertex == v) return edge.weight;
                    if (edge.numberOfSecondVertex > v) return Double.POSITIVE_INFINITY;
                }
            }
            if (vertex.numberOfFirstVertex > u)
                return Double.POSITIVE_INFINITY;    //poukladany rosnąco, wiec mozemy dodac taki warunek
        }
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public void weight(int u, int v, double w) {
        if (u < 0 || v < 0) throw new IllegalArgumentException();

        for (Vertex vertex : vertices) {
            if (vertex.numberOfFirstVertex == u) {
                for (Edge edge : vertex.edges) {
                    if (edge.numberOfSecondVertex == v) {
                        edge.weight = w;
                        return;
                    }
                    if (edge.numberOfSecondVertex > v) return;
                }
            }
            if (vertex.numberOfFirstVertex > u) return;    //poukladany rosnąco, wiec mozemy dodac taki warunek
        }
    }

    @Override
    public Iterator<Converter.WeightedEdge> edges(int v) {
        Vertex vVertex = findVertex(v);
        List<Edge> edges;
        if (vVertex == null) throw new NoSuchElementException();
        else edges = vVertex.edges;

        return new Iterator<Converter.WeightedEdge>() {
            int pos = 0;
            @Override
            public boolean hasNext() {
                return pos < edges.size();
            }

            @Override
            public Converter.WeightedEdge next() {
                if (hasNext()) {
                    Edge edge = edges.get(pos++);
                    return new Converter.WeightedEdge(v, edge.numberOfSecondVertex, edge.weight);
                } else throw new NoSuchElementException();
            }
        };
    }

    @Override
    public List<Converter.WeightedEdge> getAllPairs() {
        ArrayList<Converter.WeightedEdge> weightedEdges = new ArrayList<>(vertices.size() * vertices.size());
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.edges) {
                weightedEdges.add(new Converter.WeightedEdge(vertex.numberOfFirstVertex, edge.numberOfSecondVertex, edge.weight));
            }
        }
        return weightedEdges;
    }

    private Vertex orderedAddVertex(int vertexNumber) {
        ListIterator<Vertex> itr = vertices.listIterator();
        Vertex newVertex = new Vertex(vertexNumber);

        while (true) {
            if (!itr.hasNext()) {
                itr.add(newVertex);
                return newVertex;
            }

            Vertex elementInList = itr.next();
            if (elementInList.numberOfFirstVertex > vertexNumber) {
                itr.previous();
                itr.add(newVertex);
                return newVertex;
            } else if (elementInList.numberOfFirstVertex == vertexNumber) return elementInList;
        }
    }

    private boolean orderedAddEdge(Vertex vertex, int vertexNumber, double weight) {
        ListIterator<Edge> itr = vertex.edges.listIterator();
        Edge newEdge = new Edge(vertexNumber, weight);

        while (true) {
            if (!itr.hasNext()) {
                itr.add(newEdge);
                return true;
            }

            Edge elementInList = itr.next();
            if (elementInList.numberOfSecondVertex > vertexNumber) {
                itr.previous();
                itr.add(newEdge);
                return true;
            } else if (elementInList.numberOfSecondVertex == vertexNumber) return false;
        }
    }

    private Vertex findVertex(int vertexNumber) {

        for (Vertex elementInList : vertices) {
            if (elementInList.numberOfFirstVertex == vertexNumber) return elementInList;
            else if (elementInList.numberOfFirstVertex > vertexNumber) return null;
        }
        return null;
    }


    private class Vertex {    //w liscie przechowujemy te klase, pozwala nam to przechowac i liste krawedzi i jego numer
        private final int numberOfFirstVertex;
        private final ArrayList<Edge> edges;

        private Vertex(int numberOfVertex) {
            this.numberOfFirstVertex = numberOfVertex;
            this.edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "" + this.numberOfFirstVertex;
        }
    }

    private class Edge {
        private final int numberOfSecondVertex;
        private double weight;

        private Edge(int numberOfSecondVertex, double weight) {
            this.weight = weight;
            this.numberOfSecondVertex = numberOfSecondVertex;
        }

        @Override
        public String toString() {
            return this.numberOfSecondVertex + " " + this.weight;
        }
    }
}
