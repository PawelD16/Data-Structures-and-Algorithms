package com.company;

import java.util.Iterator;

public class FiniteSeries<E> extends Series<E> {

    private final int arrayLength;

    public FiniteSeries(SeriesIterator<E> seriesIterator, int arrayLength) {
        super(seriesIterator);
        this.arrayLength = Math.max(arrayLength, 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new SeriesIterator<>(seriesIterator.getSeriesGenerator(), arrayLength);
    }
}

