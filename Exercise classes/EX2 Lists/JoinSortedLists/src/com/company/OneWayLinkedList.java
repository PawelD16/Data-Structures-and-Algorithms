package com.company;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E> {

    private Element head;
    private Comparator<E> comparator;

    public OneWayLinkedList(Comparator<E> comparator) {
        head = null;
        this.comparator = comparator;
    }

    @Override
    public boolean add(E e) {
        Element newElement = new Element(e);
        if (head == null) {
            head = newElement;
            return true;
        }
        Element seacher = head;
        while (seacher.getNext() != null)
            seacher = seacher.getNext();
        seacher.setNext(newElement);
        return true;

    }

    @Override
    public void add(int index, E value) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element newElement = new Element(value);

        if (index == 0) {
            newElement.setNext(head);
            head = newElement;
        }
        Element actElem = getElement(index - 1);
        newElement.setNext(actElem.getNext());
        actElem.setNext(newElement);
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(E value) {
        return this.indexOf(value) >= 0;
    }

    @Override
    public E get(int index) {
        Element currentElement = this.getElement(index);
        return currentElement.getValue();
    }

    @Override
    public E set(int index, E value) {
        Element currentElement = getElement(index);
        E elemData = currentElement.getValue();
        currentElement.setValue(value);
        return elemData;
    }

    @Override
    public int indexOf(E value) {
        int position = 0;
        Element currentElement = head;
        while (currentElement != null) {
            if (currentElement.getValue().equals(value))
                return position;
            position++;
            currentElement = currentElement.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public Iterator<E> iterator() {
        return this.listIterator();
    }

    /*
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Element iteratorCurrentElement = head.getNext();

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
        if (index < 0 || head == null) throw new IndexOutOfBoundsException();
        if (index == 0) {
            E valueToReturn = head.getValue();
            head = head.getNext();
            return valueToReturn;
        }
        Element currentElement = getElement(index - 1);
        if (currentElement.getNext() == null)
            throw new IndexOutOfBoundsException();
        E valueToReturn = currentElement.getNext().getValue();
        currentElement.setNext(currentElement.getNext().getNext());
        return valueToReturn;
    }

    @Override
    public boolean remove(E value) {
        if (head == null)
            return false;

        if (head.getValue().equals(value)) {
            head = head.getNext();
            return true;
        }

        Element currentElement = head;

        while (currentElement.getNext() != null && !currentElement.getNext().getValue().equals(value))
            currentElement = currentElement.getNext();

        if (currentElement.getNext() == null) return false;

        currentElement.setNext(currentElement.getNext().getNext());
        return true;
    }

    @Override
    public int size() {
        int position = 0;
        Element currentElement = head;
        while (currentElement != null) {
            position++;
            currentElement = currentElement.getNext();
        }
        return position;
    }


    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();

        Element currentElement = head;
        while (index > 0 && currentElement != null) {
            index--;
            currentElement = currentElement.getNext();
        }

        if (currentElement == null)
            throw new IndexOutOfBoundsException();

        return currentElement;
    }

    public void sortLinkedList() {
        Element current = head, swapper = null;
        E temp;

        if (current == null) return;

        while (current != null) {

            swapper = current.getNext();
            while (swapper != null) {
                if (comparator.compare(current.getValue(), swapper.getValue()) == 1) {
                    temp = current.getValue();
                    current.setValue(swapper.getValue());
                    swapper.setValue(temp);
                }
                swapper = swapper.getNext();
            }
            current = current.getNext();
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            Element listIteratorCurrentElement = head;
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
                return listIteratorCurrentElement != head;
            }

            @Override
            public E previous() {
                if (!this.hasPrevious()) throw new NoSuchElementException();

                ifLastOperationPrevious = true;
                ifRemoveWasCalled = false;
                ifAddWasCalled = false;


                Element searcher = head;
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
                    if (!ifLastOperationPrevious){
                        OneWayLinkedList.this.remove(--position);
                    }
                    else {
                        OneWayLinkedList.this.remove(position--);
                    }
                } else throw new UnsupportedOperationException();

                ifRemoveWasCalled = true;
            }

            @Override
            public void set(E value) {
                if (!ifAddWasCalled && !ifRemoveWasCalled) {
                    if (!ifLastOperationPrevious)
                        OneWayLinkedList.this.set(position - 1, value);
                    else OneWayLinkedList.this.set(position, value);
                } else throw new UnsupportedOperationException();
            }

            @Override
            public void add(E value) {
                OneWayLinkedList.this.add(position, value);

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
            if (!(o instanceof OneWayLinkedList.Element)) return false;
            return ((Element) o).getNext().equals(this.getNext());
        }
    }
}
