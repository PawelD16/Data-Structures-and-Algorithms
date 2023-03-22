package com.company.iterator;

import com.company.predicate.Predicate;

public class GradeFilterIterator implements Iterator {

    private final Predicate predicate;
    private final Iterator iterator;

    private Object elemNext;
    private boolean bHasNext;

    public GradeFilterIterator(Iterator iterator, Predicate predicate) {
        super();
        bHasNext = true;
        elemNext = null;
        this.predicate = predicate;
        this.iterator = iterator;
        findNextValid();
    }

    private void findNextValid() {
        while (iterator.hasNext()) {
            elemNext = iterator.next();
            if (predicate.accept(elemNext)) {
                return;
            }
        }
        bHasNext = false;
        elemNext = null;
    }

    @Override
    public boolean hasNext() {
        return bHasNext;
    }

    @Override
    public Object next() {
        Object next = elemNext;
        findNextValid();
        return next;
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
