package com.company;

import java.util.LinkedList;
import java.util.Comparator;

public class SeparateChainingHashTable<T> extends HashTable<T> {

    private final HashFunction<T> hashFunction; //funkcja do inkrementacji jest zbedna!
    private LinkedList<T>[] hashArray;
    private int size, collisions, insertComparisons, lookUpComparisons, hashFunctionEvaluations;

    @SuppressWarnings("unchecked")
    protected SeparateChainingHashTable(double maxLoadFactor, Comparator<? super T> comparator, HashFunction<T> hashFunction) {
        super(maxLoadFactor, comparator);
        this.size = 0;
        this.collisions = 0;
        this.insertComparisons = 0;
        this.lookUpComparisons = 0;
        this.hashFunctionEvaluations = 0;
        this.hashFunction = hashFunction;
        this.hashArray = new LinkedList[10];
    }

    @Override
    public int capacity() {
        return hashArray.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insert(T object) {
        if(object == null) throw new IllegalArgumentException();
        if (loadFactor() >= maxLoadFactor || size() >= capacity()) {
            LinkedList<T>[] temp = new LinkedList[hashArray.length * 2];
            for (int i = 0; i < capacity(); i++) {
                if (hashArray[i] != null && !hashArray[i].isEmpty()) {
                    for (T value : hashArray[i]) {
                        temp[findHashCode(temp, value)].add(value);
                    }
                }
            }
            hashArray = temp;
        }

        int x = findHashCode(hashArray, object);

        if (x != -1) {
            hashArray[x].add(object);
            size++;
        }
    }

    private int findHashCode(LinkedList<T>[] array, T object) {
        hashFunctionEvaluations++;

        int hashPos = Math.abs(hashFunction.hashCode(object) % array.length);

        if (array[hashPos] != null && !array[hashPos].isEmpty()) {
            collisions++;
            for (T checker : array[hashPos]) {
                insertComparisons++;
                if (comparator.compare(checker, object) == 0) return -1;
            }

        } else {
            array[hashPos] = new LinkedList<>();
        }
        return Math.abs(hashPos);
    }

    @Override
    public boolean lookUp(T object) {
        if(object == null) throw new IllegalArgumentException();
        LinkedList<T> temp = hashArray[Math.abs(hashFunction.hashCode(object) % capacity())];
        if (temp == null) return false;

        for (T checker : temp) {
            lookUpComparisons++;
            if (comparator.compare(checker, object) == 0) return true;
        }
        return false;
        /*
        for (int i = 0, k = 0; i < capacity() && k < size(); i++) {
            if (hashArray[i] != null) {
                for(T checker : hashArray[i]) {
                    k++;
                    lookUpComparisons++;
                    if (comparator.compare(checker, object) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;

         */
    }

    @Override
    public int collisions() {
        return collisions;
    }

    @Override
    public int insertComparisons() {
        return insertComparisons;
    }

    @Override
    public int lookUpComparisons() {
        return lookUpComparisons;
    }

    @Override
    public int hashFunctionEvaluations() {
        return hashFunctionEvaluations;
    }

    @Override
    public boolean ifContainsRepeats() {
        for (int i = 0; i < capacity(); i++) {
            if (hashArray[i] != null) {
                for (int j = 0; j < hashArray[i].size(); j++) {
                    for (int k = j + 1; k < hashArray[i].size(); k++) {
                        if (hashArray[i].get(j) != null && hashArray[i].get(k) != null && comparator.compare(hashArray[i].get(j), hashArray[i].get(k)) == 0)
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder k = new StringBuilder("[");
        for (int i = 0; i < capacity(); i++) {
            if (hashArray[i] != null && !hashArray[i].isEmpty()) {
                String string = hashArray[i].toString();
                string = string.replace("[", "");
                string = string.replace("]", "");
                k.append(string);
            } else k.append(" ");
            if (i + 1 < capacity()) k.append("|");
        }
        k.append("]");

        return String.valueOf(k);
    }
}
