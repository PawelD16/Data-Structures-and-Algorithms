package com.company;

import java.util.Comparator;

public interface IHeap<T> {
    void clear();
    void add(T element);
    T minimum();
    void print();
    int getSize();
    Comparator<T> getComparator();
}
