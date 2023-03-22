package com.company;

import java.util.NoSuchElementException;

public class SeriesIterator<E> implements java.util.Iterator<E> {
    private int pos = 1, maxLength = -1;
    private final SeriesGenerator<E> seriesGenerator;

    public SeriesIterator(SeriesGenerator<E> seriesGenerator) {
        this.seriesGenerator = seriesGenerator;
    }

    public SeriesIterator(SeriesGenerator<E> seriesGenerator, int maxLength) {
        this.seriesGenerator = seriesGenerator;
        this.maxLength = Math.max(maxLength, 0);
    }

    @Override
    public boolean hasNext() {
        if(maxLength == -1) return seriesGenerator != null && pos<Integer.MAX_VALUE;
        return seriesGenerator != null && maxLength >= 0 && pos < maxLength && pos<Integer.MAX_VALUE;
    }

    @Override
    public E next() {
        pos = Math.max(pos,(-1)*pos);
        if (hasNext()) return seriesGenerator.generate(pos++);
        else throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public SeriesGenerator<E> getSeriesGenerator() {
        return seriesGenerator;
    }

}
