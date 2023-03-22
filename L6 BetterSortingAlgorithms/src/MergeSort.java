import core.SortingAlgorithm;

import java.util.*;

public class MergeSort<T> extends SortingAlgorithm<T> {
    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        ListIterator<T> iterator = list.listIterator();
        LinkedList<T[]> queue = new LinkedList<>();
        LinkedList<T[]> queue2 = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            queue.addLast((T[]) new Object[]{iterator.next()});
        }

        /*
        while (queue.size() > 1) { //tracimy stabilność, przez implementacje z kolejką
            queue.addLast(mergeArrays(queue.get(0), queue.get(1)));
            queue.removeFirst();
            queue.removeFirst();
        }
         */

        while (queue.size() > 1) {

            while (!queue.isEmpty()) {
                if (queue.size() == 1) {
                    queue2.addLast(queue.get(0));
                    queue.removeFirst();
                } else {
                    queue2.addLast(mergeArrays(queue.get(0), queue.get(1)));
                    queue.removeFirst();
                    queue.removeFirst();
                }
            }
            queue.addAll(queue2);
            queue2.clear();
        }

        list.clear();
        list.addAll(Arrays.asList(queue.get(0)));
        return list;
    }

    @SuppressWarnings("unchecked")
    public T[] mergeArrays(T[] array1, T[] array2) {

        T[] newArray = (T[]) new Object[array1.length + array2.length];

        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {

            if (compare(array1[i], array2[j]) <= 0) {
                newArray[k] = array1[i];
                i++;
            } else {
                newArray[k] = array2[j];
                j++;
            }
            k++;
        }

        while (i < array1.length) {
            newArray[k] = array1[i];
            i++;
            k++;
        }

        while (j < array2.length) {
            newArray[k] = array2[j];
            j++;
            k++;
        }

        return newArray;
    }


/*
    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        for (int currentSizeOfSubList = 1; currentSizeOfSubList <= size - 1; currentSizeOfSubList = 2 * currentSizeOfSubList) {
            for (int startOfCurrentSubList = 0; startOfCurrentSubList < size - 1; startOfCurrentSubList += 2 * currentSizeOfSubList) {

                int endOfCurrentSubList = Math.min(startOfCurrentSubList + currentSizeOfSubList - 1, size - 1);
                int endOfNextSubList = Math.min(startOfCurrentSubList + 2 * currentSizeOfSubList - 1, size - 1);

                mergeList(list, startOfCurrentSubList, endOfCurrentSubList, endOfNextSubList);
            }
        }

        return list;
    }


    @SuppressWarnings("unchecked")
    public void mergeList(List<T> list, int startOfCurrentSubList, int endOfCurrentSubList, int endOfNextSubList) {

        int lengthOfLeft = endOfCurrentSubList - startOfCurrentSubList + 1;
        int lengthOfRight = endOfNextSubList - endOfCurrentSubList;

        T[] left = (T[]) new Object[lengthOfLeft];
        T[] right = (T[]) new Object[lengthOfRight];

        for (int x = 0; x < lengthOfLeft; x++) {
            left[x] = list.get(startOfCurrentSubList + x);
        }

        for (int y = 0; y < lengthOfRight; y++) {
            right[y] = list.get(endOfCurrentSubList + 1 + y);
        }

        int i = 0, j = 0, k = startOfCurrentSubList;
        while (i < lengthOfLeft && j < lengthOfRight) {

            if (compare(left[i], right[j]) <= 0) {
                list.set(k, left[i]);
                i++;
            } else {
                list.set(k, right[j]);
                j++;
            }
            //swap(list, i, i); //do liczenia swapów
            k++;
        }

        while (i < lengthOfLeft) {
            list.set(k, left[i]);
            i++;
            k++;
            //swap(list, i, i); //do liczenia swapów
        }

        while (j < lengthOfRight) {
            list.set(k, right[j]);
            j++;
            k++;
            //swap(list, i, i); //do liczenia swapów
        }
    }
     */
}
