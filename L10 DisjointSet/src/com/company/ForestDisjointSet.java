package com.company;

public class ForestDisjointSet { //heurystyka "metoda znajdowania rozwiązań, dla której nie ma gwarancji znalezienia rozwiązania optymalnego"

    private ForestDisjointSet parent;
    private int rank;

    public ForestDisjointSet() {
    }

    public ForestDisjointSet makeSet() {
        this.parent = this;
        this.rank = 0;
        return this;
    }

    public void union(ForestDisjointSet y) {
        if (this == y) throw new IllegalArgumentException("You cannot union a set to itself!");
        ForestDisjointSet x = this.findSet();
        y = y.findSet();
        if (x.rank > y.rank) {
            y.parent = x;
        } else {
            x.parent = y;
            if (x.rank == y.rank) y.rank++;
        }
    }

    public ForestDisjointSet findSet() {
        if (this.parent != this) {
            this.parent = this.parent.findSet(); //heurystyka 2 (kompresja ścieżki), nie zmniejszamy rangi, ponieważ heurystyka tego nie wymaga
        }
        return this.parent;
    }
}
