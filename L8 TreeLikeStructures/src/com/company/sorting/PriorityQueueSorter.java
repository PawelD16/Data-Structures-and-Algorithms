package com.company.sorting;

import com.company.IHeap;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class PriorityQueueSorter<T> {
    IHeap<T> heap;
    long sortTime;

    public PriorityQueueSorter(IHeap<T> heap) {
        this.heap = heap;
        sortTime = 0;
    }

    public List<T> sort(List<T> list) {
        heap.clear();
        int size = list.size();

        Instant start = Instant.now();

        for (T obj : list) {
            heap.add(obj);
        }

        for (int i = 0; i < size; i++) {
            list.set(i, heap.minimum());
        }

        Instant end = Instant.now();

        if(ListChecker.isSorted(list, heap.getComparator())) sortTime = Duration.between(start, end).toMillis();
        else sortTime = -1;

        return list;
    }


    public void printStatistic(String label) {
        System.out.println(label + ": " + double2String(sortTime));
    }

    private String double2String(double value) {
        return String.format("%.12f", value);
    }
}
