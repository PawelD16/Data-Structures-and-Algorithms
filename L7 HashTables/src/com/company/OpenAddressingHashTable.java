package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class OpenAddressingHashTable<T> extends HashTable<T> {

    private final HashFunction<T> hashFunction;
    private final IncrementalFunction<T> incrementalFunction;
    private T[] hashArray;
    private int size, collisions, insertComparisons, lookUpComparisons, hashFunctionEvaluations;

    @SuppressWarnings("unchecked")
    protected OpenAddressingHashTable(double maxLoadFactor, Comparator<? super T> comparator, HashFunction<T> hashFunction, IncrementalFunction<T> incrementalFunction) {
        super(maxLoadFactor, comparator);
        this.size = 0;
        this.collisions = 0;
        this.insertComparisons = 0;
        this.lookUpComparisons = 0;
        this.hashFunctionEvaluations = 0;
        this.hashFunction = hashFunction;
        this.incrementalFunction = incrementalFunction;
        this.hashArray = (T[]) new Object[10];
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
            T[] temp = (T[]) new Object[hashArray.length * 2];
            for (int i = 0; i < capacity(); i++) {
                if (hashArray[i] != null) {
                    temp[findHashCode(temp, hashArray[i])] = hashArray[i];
                }
            }
            hashArray = temp;
        }

        int x = findHashCode(hashArray, object);

        if (x != -1) {
            hashArray[x] = object;
            size++;
        }
    }

    private int findHashCode(T[] array, T object) {
        hashFunctionEvaluations++;

        int hashPos = hashFunction.hashCode(object) % array.length, i = 0;
        if (array[hashPos] == null) return hashPos;
        else {
            collisions++;
            for (T temp = array[hashPos]; temp != null; temp = array[findAnAddress((hashPos + incrementalFunction.shift(object, i)) % array.length)]) {

                i++;
                collisions++;
                hashFunctionEvaluations++;
                insertComparisons++;
                if (comparator.compare(temp, object) == 0) return -1;
            }
            return Math.abs(findAnAddress(hashPos + incrementalFunction.shift(object, i)) % array.length);
        }
    }

    private int findAnAddress(int n) {
        if (n < 0) return capacity() + (n % capacity());
        return n;
    }

    @Override
    public boolean lookUp(T object) {
        if(object == null) throw new IllegalArgumentException();
        int hashPos = hashFunction.hashCode(object) % capacity(), i = 0;

        while (hashArray[Math.abs((hashPos + incrementalFunction.shift(object, i)) % capacity())] != null) {
            lookUpComparisons++;
            if (comparator.compare(hashArray[Math.abs((hashPos + incrementalFunction.shift(object, i)) % capacity())], object) == 0)
                return true;
            i++;
            collisions++;
        }
        return false;
        /*
        for (int i = 0, k = 0; i < capacity() && k < size(); i++) {
            if (hashArray[i] != null) {
                k++;
                lookUpComparisons++;
                if (comparator.compare(hashArray[i], object) == 0) {
                    return true;
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
            for (int j = i + 1; j < capacity(); j++) {
                if (hashArray[i] != null && hashArray[j] != null && comparator.compare(hashArray[j], hashArray[i]) == 0)
                    return true;
            }
        }
        return false;
    }

    /*
    @Override
    public boolean ifContainsRepeats() {

        for (int i = 0; i < capacity(); i++) {
            for (int j = i + 1; j < capacity(); j++){
                if(hashArray[i] != null & hashArray[j] != null && comparator.compare(hashArray[i], hashArray[j]) == 0) return true;
            }
        }
        return false;
    }

     */

    @Override
    public String toString() {
        return Arrays.toString(hashArray);
    }
}
