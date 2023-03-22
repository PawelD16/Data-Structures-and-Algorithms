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

    public void add(Object obj) {

        if (hasNext() && !array[pos].getClass().equals(obj.getClass())) {
            throw new UnsupportedOperationException();
        }

        Object[] newArray = new Object[array.length + 1];
        newArray[array.length] = obj;

        for (int i = 0; i < pos; i++) {
            newArray[i] = array[i];
        }

        for (int i = newArray.length - 1; i > pos; i--) {
            newArray[i] = array[i - 1];
        }

        array = newArray;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getArray() {
        return array;
    }

    @Override
    public int getPos() {
        return pos;
    }

    public void swap() {
        Object temp = array[pos];
        array[pos] = array[pos - 1];
        array[pos - 1] = temp;
    }
}
