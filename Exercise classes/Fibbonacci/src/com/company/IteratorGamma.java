package com.company;

public interface IteratorGamma <T>{

    void first();
    void last();
    void next();
    void previous();
    boolean isDone();
    T current();
}
