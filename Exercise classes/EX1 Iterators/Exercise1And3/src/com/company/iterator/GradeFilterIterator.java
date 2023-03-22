package com.company.iterator;

import com.company.Predicate;

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

    public GradeFilterIterator(Object[] array, Predicate predicate) {
        super();
        bHasNext = true;
        elemNext = null;
        this.predicate = predicate;
        this.iterator = new BasicIterator(array);
        findNextValid();
    }

    private void findNextValid() {
        while (iterator.hasNext()) {
            elemNext = iterator.next();
            if (predicate.accept(elemNext)) return;
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

    @Override
    public Object[] getArray() {
        int i;
        GradeFilterIterator tempGradeFilterIterator = new GradeFilterIterator(new BasicIterator(iterator.getArray()), predicate);
         i = 0;

        while (tempGradeFilterIterator.hasNext()) {
            tempGradeFilterIterator.next();
            i++;
        }

        tempGradeFilterIterator = new GradeFilterIterator(new BasicIterator(iterator.getArray()), predicate);
        Object[] tempArray = new Object[i];

        for (i = 0; i < tempArray.length; i++) {
            tempArray[i] = tempGradeFilterIterator.next();
        }

        return tempArray;
        //return iterator.getArray();
    }

    @Override
    public int getPos() {
        return iterator.getPos();
    }
}
