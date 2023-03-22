package com.company.iterator;

import java.util.NoSuchElementException;

public class BasicIterator implements Iterator {

    protected int pos = 0;
    protected Object[] array;

    public BasicIterator(Object[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return pos < array.length;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (hasNext()) return array[pos++];
        else throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
