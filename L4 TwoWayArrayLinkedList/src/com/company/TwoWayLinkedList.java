package com.company;

import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements IList<E> {

    private Node head;
    private final int sizeOfArrays;

    public TwoWayLinkedList(Integer sizeOfArrays) {
        if (sizeOfArrays == null || sizeOfArrays <= 0) throw new IllegalArgumentException();
        this.head = null;
        this.sizeOfArrays = sizeOfArrays;
    }

    public void defragment() {
        Node searcher = head;

        while (searcher.getNext() != null) {
            while (searcher.remainingCapacity() > 0 && searcher.getNext() != null) {
                searcher.addValue(searcher.getNext().remove(0, true));
            }
            searcher = searcher.getNext();
        }
    }

    @Override
    public boolean add(E value) {
        if (head == null) {
            head = new Node(sizeOfArrays);
            head.addValue(value);
        } else {
            Node searcher = head;

            while (searcher.getNext() != null) {
                searcher = searcher.getNext();
            }

            if (searcher.ifHasSpace()) {
                searcher.addValue(value);

            } else {
                searcher.insertNewNodeAfterThisOne(new Node(sizeOfArrays));
                searcher.getNext().addValue(value);
            }
        }
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();

        if (index == this.size() - 1) {
            this.add(value);
            return;
        }

        if (head == null) {
            head = new Node(sizeOfArrays);
            head.addValue(value);
        } else {
            Node searcher = head;
            int size = 0;
            while (searcher.getNext() != null && searcher.getOccupiedSize() + size <= index) {
                size += searcher.getOccupiedSize();
                searcher = searcher.getNext();
            }

            if (searcher.ifHasSpace()) searcher.addValueOnIndex(value, index - size);
            else {

                Node newNode = new Node(sizeOfArrays);

                for (int i = 0; i < (sizeOfArrays - (sizeOfArrays / 2)); i++) {
                    newNode.addValue(searcher.remove((sizeOfArrays / 2), false));
                }

                if (index - size <= searcher.occupiedSize) searcher.addValueOnIndex(value, index - size);
                else newNode.addValueOnIndex(value, index - size - searcher.occupiedSize);

                searcher.insertNewNodeAfterThisOne(newNode);
            }
        }

    }


    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
        Node searcher = head;
        int size = 0;

        while (searcher.getNext() != null && searcher.getOccupiedSize() + size <= index) {
            size += searcher.getOccupiedSize();
            searcher = searcher.getNext();
        }

        return searcher.getValue(index - size);
    }

    @Override
    public E set(int index, E value) {
        Node searcher = head;
        int size = 0;

        while (searcher.getNext() != null && searcher.getOccupiedSize() + size <= index) {
            size += searcher.getOccupiedSize();
            searcher = searcher.getNext();
        }

        return searcher.setValue(value, index - size);
    }

    @Override
    public int indexOf(E value) {
        Node searcher = head;
        int size = 0;
        if (head != null && head.getNext() == null && searcher.indexOf(value) != -1) return searcher.indexOf(value);
        while (searcher.getNext() != null) {
            size += searcher.getOccupiedSize();
            if (searcher.indexOf(value) != -1) return size - (searcher.getOccupiedSize() - searcher.indexOf(value));
            searcher = searcher.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node iteratorCurrentElement = head;
            int pos = 0;

            @Override
            public boolean hasNext() {
                return iteratorCurrentElement != null;
            }

            @Override
            public E next() {
                E currentValue = iteratorCurrentElement.getValue(pos);
                pos++;
                if (/*pos >= iteratorCurrentElement.occupiedSize*/pos >= iteratorCurrentElement.values.length || currentValue == null) {
                    iteratorCurrentElement = iteratorCurrentElement.getNext();
                    pos = 0;
                }
                return currentValue;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
        int size = 0;
        Node searcher = head;

        while (searcher != null) {
            if (searcher.getOccupiedSize() + size > index && size <= index) {
                return searcher.remove(index - size, true);
            } else {
                size += searcher.getOccupiedSize();
                searcher = searcher.getNext();
            }
        }
        return null;
    }

    @Override
    public E remove(E value) {
        if (value == null) return null;
        return this.remove(indexOf(value));
    }

    @Override
    public int size() {
        Node searcher = head;
        int size = 0;
        while (searcher != null) {
            size += searcher.getOccupiedSize();
            searcher = searcher.getNext();
        }
        return size;
    }

    public int capacity() {
        Node searcher = head;
        int capacity = 0;
        while (searcher != null) {
            capacity += searcher.remainingCapacity();
            searcher = searcher.getNext();
        }
        return capacity;
    }

    public int endCapacity() {
        Node searcher = head;

        while (searcher.getNext() != null) {
            searcher = searcher.getNext();
        }

        return searcher.remainingCapacity();
    }

    private class Node {
        private final E[] values;
        private Node nextNode;
        private Node previousNode;
        private int occupiedSize;

        @SuppressWarnings("unchecked")
        Node(int size) {
            this.values = (E[]) new Object[size];
            this.occupiedSize = 0;
        }

        public E getValue(int index) {
            return values[index];
        }

        public void addValue(E value) {
            if (ifHasSpace()) values[occupiedSize++] = value;
        }

        public void addValueOnIndex(E value, int index) {
            if (index < 0 || index > occupiedSize) throw new IndexOutOfBoundsException();

            if (ifHasSpace()) {
                /*
                for (int i = occupiedSize - 1; i >= index; i--) {
                    values[i + 1] = values[i];
                }
                 */
                System.arraycopy(values, index, values, index + 1, occupiedSize - index); //to robi to samo co powyższy for loop
                values[index] = value;
                occupiedSize++;
            }
        }

        public E setValue(E value, int index) {
            if (index < 0 || index > occupiedSize) throw new IndexOutOfBoundsException();
            E valueToReturn = values[index];
            values[index] = value;
            return valueToReturn;
        }

        public Node getNext() {
            return nextNode;
        }

        public void setNext(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevious() {
            return previousNode;
        }

        public void setPrevious(Node previousNode) {
            this.previousNode = previousNode;
        }

        public E remove(int index, boolean ifDeleteEmpty) {
            E valueToReturn;

            valueToReturn = values[index];
            if (values.length - 1 - index >= 0)
                System.arraycopy(values, index + 1, values, index, values.length - 1 - index);
            if (occupiedSize > 0) values[--occupiedSize] = null;
            else occupiedSize--;

            if (this.occupiedSize == 0 && ifDeleteEmpty) this.removeNode();
            return valueToReturn;
        }

        public boolean ifHasSpace() {
            return values.length > occupiedSize;
        }

        public int indexOf(E value) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] == value) return i;
            }
            return -1;
        }

        public int getOccupiedSize() {
            return occupiedSize;
        }

        public int remainingCapacity() {
            return values.length - occupiedSize;
        }

        public void insertNewNodeAfterThisOne(Node elem) {
            elem.setNext(this.getNext());
            elem.setPrevious(this);
            this.setNext(elem);
        }

        public void removeNode() {
            if (this == head) {
                head = this.getNext();
                head.previousNode = null;
            } else if (this.getNext() == null) this.getPrevious().setNext(null);
            else {
                this.getNext().setPrevious(this.getPrevious());
                this.getPrevious().setNext(this.getNext());
            }

            this.setNext(null);
            this.setPrevious(null);
        }
    }
}
