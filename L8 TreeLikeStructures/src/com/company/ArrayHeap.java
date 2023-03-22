package com.company;

import java.util.*;

public class ArrayHeap<T> implements IHeap<T> {

    private T[] array;
    private int size;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public ArrayHeap(int size, Comparator<T> comparator) {
        this.array = (T[]) new Object[size];
        this.comparator = comparator;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        this.size = 0;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void add(T element) {
        if(element == null) throw new IllegalArgumentException();

        if (size == array.length) {
            T[] tempArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, tempArray, 0, array.length);
            array = tempArray;
        }

        array[size] = element;
        int temp = size;
        size++;

        while (comparator.compare(array[temp], array[parent(temp)]) < 0) {
            swap(temp, parent(temp));
            temp = parent(temp);
        }
    }

    @Override
    public T minimum() {
        T temp = array[0];
        array[0] = array[--size];
        if (size > 0) remakeHeapAt(0);
        array[size] = null;

        return temp;
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos < size;
    }

    private void remakeHeapAt(int pos) {
        if (array[pos] != null) {

            if (!isLeaf(pos)) {
                if (comparator.compare(array[pos], array[leftChild(pos)]) > 0 || comparator.compare(array[pos], array[rightChild(pos)]) > 0) {

                    if (comparator.compare(array[leftChild(pos)], array[rightChild(pos)]) < 0) {
                        swap(pos, leftChild(pos));
                        remakeHeapAt(leftChild(pos));
                    } else {
                        swap(pos, rightChild(pos));
                        remakeHeapAt(rightChild(pos));
                    }
                }
            }
        }
    }

    private void swap(int left, int right) {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void print() {

        for (int i = 0; i <= (int) Math.ceil(Math.log(size + 1) / Math.log(2)) - 1; i++) {
            for (int j = 0; j < Math.pow(2, i) && j + Math.pow(2, i) <= size; j++) {
                System.out.print(array[j + (int) Math.pow(2, i) - 1] + "\t");
            }
            System.out.println();
        }
    }
}
