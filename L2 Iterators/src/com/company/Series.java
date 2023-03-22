package com.company;

import java.util.Iterator;
import java.util.function.Consumer;

public class Series<E> implements Iterable<E> {

    protected SeriesIterator<E> seriesIterator;

    public Series(SeriesIterator<E> seriesIterator) {
        if(seriesIterator == null) seriesIterator = new SeriesIterator<>(null);
        this.seriesIterator = new SeriesIterator<>(seriesIterator.getSeriesGenerator());
    }

    @Override
    public Iterator<E> iterator() {
        return seriesIterator;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }
}
