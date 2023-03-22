package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithSentinel<E> implements IList<E> {

    private final Element sentinel;

    public OneWayLinkedListWithSentinel() {
        this.sentinel = new Element(null); //creating the sentinel
    }

    @Override
    public boolean add(E e) {
        Element searcher = sentinel;
        while (searcher.getNext() != null) searcher = searcher.getNext();

        searcher.setNext(new Element(e));
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0) throw new IndexOutOfBoundsException();

        Element newElement = new Element(value);

        Element currentElement = getElement(index - 1);
        newElement.setNext(currentElement.getNext());
        currentElement.setNext(newElement);
    }

    @Override
    public void clear() {
        sentinel.setNext(null);
    }

    @Override
    public boolean contains(E value) {
        return this.indexOf(value) >= 0;
    }

    @Override
    public E get(int index) {
        return this.getElement(index).getValue();
    }

    @Override
    public E set(int index, E value) {
        E changedValue = this.get(index);
        this.getElement(index).setValue(value);

        return changedValue;
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        Element searcher = sentinel.getNext();

        while (searcher != null) {
            if (searcher.getValue().equals(value)) return index;
            searcher = searcher.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.getNext() == null;
    }

    public Iterator<E> iterator() {
        return this.listIterator();
    }
/*
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Element iteratorCurrentElement = sentinel.getNext();

            @Override
            public boolean hasNext() {
                return iteratorCurrentElement != null;
            }

            @Override
            public E next() {
                E currentValue = iteratorCurrentElement.getValue();
                iteratorCurrentElement = iteratorCurrentElement.getNext();
                return currentValue;
            }
        };
    }
 */


    @Override
    public E remove(int index) {
        Element currentElement = this.getElement(index - 1);
        Element tempElement;
        E valueToReturn;

        if (currentElement.getNext() == null) throw new IndexOutOfBoundsException();
        else {
            valueToReturn = currentElement.getNext().getValue();
            tempElement = currentElement.getNext().getNext();
            currentElement.setNext(tempElement);
        }

        return valueToReturn;
    }

    @Override
    public boolean remove(E value) {
        if (value == null || !this.contains(value)) return false;
        this.remove(this.indexOf(value));
        return true;
    }

    @Override
    public int size() {
        int sizeCounter = 0;
        Element currentElement = sentinel.getNext();

        while (currentElement != null) {
            currentElement = currentElement.getNext();
            sizeCounter++;
        }
        return sizeCounter;
    }

    @Override
    public void reverse() {
        Element tempElement = sentinel;
        Element tempElement1 = sentinel.getNext();

        if (tempElement.getNext() != null && tempElement1.getNext() != null) {

            Element tempElement2 = tempElement1.getNext();

            while (true) {
                if (tempElement == sentinel) tempElement1.setNext(null);
                else tempElement1.setNext(tempElement);

                if (tempElement2 == null) {
                    sentinel.setNext(tempElement1);
                    break;
                } else {
                    tempElement = tempElement1;
                    tempElement1 = tempElement2;
                    tempElement2 = tempElement2.getNext();
                }
            }
        }
    }

    private Element getElement(int index) {
        if (index >= this.size()) throw new IndexOutOfBoundsException();
        else {
            Element searcher = sentinel;

            while (index >= 0) {
                index--;
                searcher = searcher.getNext();
            }
            return searcher;
        }
    }


    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            Element listIteratorCurrentElement = sentinel.getNext();
            int position = 0;
            boolean ifLastOperationPrevious = false, ifAddWasCalled = true, ifRemoveWasCalled = true;

            @Override
            public boolean hasNext() {
                return listIteratorCurrentElement != null;
            }

            @Override
            public E next() {
                if (!this.hasNext()) throw new NoSuchElementException();

                ifLastOperationPrevious = false;
                ifRemoveWasCalled = false;
                ifAddWasCalled = false;


                E value = listIteratorCurrentElement.getValue();
                listIteratorCurrentElement = listIteratorCurrentElement.getNext();
                position++;

                return value;
            }

            @Override
            public boolean hasPrevious() {
                return listIteratorCurrentElement != sentinel.getNext();
            }

            @Override
            public E previous() {
                if (!this.hasPrevious()) throw new NoSuchElementException();

                ifLastOperationPrevious = true;
                ifRemoveWasCalled = false;
                ifAddWasCalled = false;


                Element searcher = sentinel;
                while (searcher.getNext() != null && searcher.getNext() != listIteratorCurrentElement)
                    searcher = searcher.getNext();
                listIteratorCurrentElement = searcher;
                position = Math.max(position - 1, 0);
                return searcher.getValue();
            }

            @Override
            public int nextIndex() {
                if (!this.hasNext()) throw new NoSuchElementException();

                Element tempElement = listIteratorCurrentElement;
                position = 0;
                while (hasPrevious()) previous();

                while (hasNext() && tempElement != listIteratorCurrentElement) {
                    next();
                }

                listIteratorCurrentElement = tempElement;
                return position;
            }

            @Override
            public int previousIndex() {
                if (!this.hasPrevious()) throw new NoSuchElementException();

                Element tempElement = listIteratorCurrentElement;
                position = 0;
                while (hasPrevious()) {
                    previous();
                }
                while (hasNext() && tempElement != listIteratorCurrentElement) {
                    next();
                }

                listIteratorCurrentElement = tempElement;
                return position - 1;
            }

            @Override
            public void remove() {
                if (!ifAddWasCalled && !ifRemoveWasCalled) {
                    if (!ifLastOperationPrevious) {
                        OneWayLinkedListWithSentinel.this.remove(--position);
                    } else {
                        OneWayLinkedListWithSentinel.this.remove(position--);
                    }
                } else throw new UnsupportedOperationException();

                ifRemoveWasCalled = true;
            }

            @Override
            public void set(E value) {
                if (!ifAddWasCalled && !ifRemoveWasCalled) {
                    if (!ifLastOperationPrevious)
                        OneWayLinkedListWithSentinel.this.set(position - 1, value);
                    else OneWayLinkedListWithSentinel.this.set(position, value);
                } else throw new UnsupportedOperationException();
            }

            @Override
            public void add(E value) {
                OneWayLinkedListWithSentinel.this.add(position, value);

                previous();
                position++;
                ifAddWasCalled = true;
            }
        };
    }

    private class Element {
        private E value;
        private Element next;

        Element(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof OneWayLinkedListWithSentinel.Element)) return false;
            return ((Element) o).getNext().equals(this.getNext());
        }
    }
}
