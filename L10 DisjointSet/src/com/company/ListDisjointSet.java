package com.company;

public class ListDisjointSet {
    private ListDisjointSet nextSet;
    private ListDisjointSet lastSet;
    private ListDisjointSet representative;
    private int size;

    public ListDisjointSet() {

    }

    public ListDisjointSet makeSet() {
        this.representative = this;
        this.lastSet = this;
        this.size = 1;

        return this;
    }

    public void union(ListDisjointSet x) {
        if (x==this) throw new IllegalArgumentException("You cannot union a set to itself!");
        ListDisjointSet a, b;

        if (this.size >= x.size) { //porówywanie wielkości
            a = this.findSet();
            b = x.findSet();
        } else {
            b = this.findSet();
            a = x.findSet();
        }

        int sizeOfB = b.size;
        a.size += sizeOfB;
        a.lastSet.nextSet = b;
        a.lastSet = b.lastSet;
        b.lastSet = null;
        b.size = 0;

        for (int i = 0; i < sizeOfB; ++i) {
            b.representative = a;
            b = b.nextSet;
        }
    }

    public ListDisjointSet findSet() {
        return this.representative;
    }
}
