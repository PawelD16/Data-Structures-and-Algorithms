package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E>  implements IList<E> {

    private Element head;

    public DoublyLinkedList(){
        head = null;
    }


    @Override
    public boolean add(E value) {
        Element newElement = new Element(value);
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
        Element currentElement = this.getElement(index - 1);
        newElement.setNext(currentElement.getNext());
        newElement.setPrevious(currentElement);
        currentElement.setNext(newElement);
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

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
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
                return listIteratorCurrentElement.getPrevious() != null;
            }

            @Override
            public E previous() {
                if (!this.hasPrevious()) throw new NoSuchElementException();

                ifLastOperationPrevious = false;
                ifRemoveWasCalled = false;
                ifAddWasCalled = false;


                E value = listIteratorCurrentElement.getValue();
                listIteratorCurrentElement = listIteratorCurrentElement.getPrevious();

                position = Math.max(position - 1, 0);
                return value;
            }

            @Override
            public int nextIndex() {
                if (!this.hasNext()) throw new NoSuchElementException();

                return position;
            }

            @Override
            public int previousIndex() {
                if (!this.hasPrevious()) throw new NoSuchElementException();

                return position - 1;
            }

            @Override
            public void remove() {
                if (!ifAddWasCalled && !ifRemoveWasCalled) {
                    if (!ifLastOperationPrevious){
                        DoublyLinkedList.this.remove(--position);
                    }
                    else {
                        DoublyLinkedList.this.remove(position--);
                    }
                } else throw new UnsupportedOperationException();

                ifRemoveWasCalled = true;
            }

            @Override
            public void set(E value) {
                if (!ifAddWasCalled && !ifRemoveWasCalled) {
                    if (!ifLastOperationPrevious)
                        DoublyLinkedList.this.set(position - 1, value);
                    else DoublyLinkedList.this.set(position, value);
                } else throw new UnsupportedOperationException();
            }

            @Override
            public void add(E value) {
                DoublyLinkedList.this.add(position, value);

                previous();
                position++;
                ifAddWasCalled = true;
            }
        };
    }

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
        currentElement.getNext().getNext().setPrevious(currentElement);

        return valueToReturn;
    }

    @Override
    public boolean remove(E value) {
        if (!this.contains(value)) return false;
        this.remove(this.indexOf(value));
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

    private class Element {
        private E value;
        private Element next, previous;

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

        public void setPrevious(Element next) {
            this.previous = previous;
        }

        public Element getPrevious() {
            return previous;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (!(o instanceof DoublyLinkedList.Element)) return false;
            return ((Element) o).getNext().equals(this.getNext()) && ((Element) o).getPrevious().equals(this.getPrevious())  && ((Element) o).getValue().equals(this.getValue());
        }
    }
}
