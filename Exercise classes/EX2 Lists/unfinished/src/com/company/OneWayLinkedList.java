package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> {

    private Element head;
    private int size;

    public OneWayLinkedList() {
        head = null;
        size = 0;
    }

    public void circulate() {
        Element currentElement = head;
        while (currentElement.getNext() != null) {
            currentElement = currentElement.getNext();
        }
        currentElement.setNext(head);

    }


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
        size++;
        return true;

    }


    public void clear() {
        head = null;
    }


    public boolean contains(E value) {
        return this.indexOf(value) >= 0;
    }

    public E get(int index) {
        Element currentElement = this.getElement(index);
        return currentElement.getValue();
    }

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
        size--;
        return valueToReturn;
    }

    public boolean remove(E value) {
        remove(indexOf(value));
        return true;
    }


    public int size() {
        return size;
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


    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {

            Element listIteratorCurrentElement = head;
            int position = 1;
            boolean ifLastOperationPrevious = false, ifAddWasCalled = true, ifRemoveWasCalled = true;

            @Override
            public boolean hasNext() {
                return true;
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
                return true;
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
                    System.out.println(listIteratorCurrentElement.getValue());
                    OneWayLinkedList.this.remove(listIteratorCurrentElement.getValue());
                } else throw new UnsupportedOperationException();

                ifRemoveWasCalled = true;
            }

            @Override
            public void set(E e) {
                return;
            }

            @Override
            public void add(E e) {
                return;
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
