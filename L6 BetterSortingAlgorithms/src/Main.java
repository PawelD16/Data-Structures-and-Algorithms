
import java.util.Comparator;
import java.util.Random;

import core.SortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

    public static void main(String[] args) {

        int n = 40000;

        //MergeSort

        System.out.println("\n\nMerge sort:");
        Comparator<MarkedValue<Integer>> markedComparatorMergeSort = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generatorMergeSort = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(n));

        SortingAlgorithm<MarkedValue<Integer>> algorithmMergeSort = new MergeSort<>(markedComparatorMergeSort);

        Result resultMergeSort = Tester.runNTimes(algorithmMergeSort, generatorMergeSort, n, 20);

        printStatistic("time [ms]", resultMergeSort.averageTimeInMilliseconds(), resultMergeSort.timeStandardDeviation());
        printStatistic("comparisons", resultMergeSort.averageComparisons(), resultMergeSort.comparisonsStandardDeviation());
        printStatistic("swaps", resultMergeSort.averageSwaps(), resultMergeSort.swapsStandardDeviation());

        System.out.println("always sorted: " + resultMergeSort.sorted());
        System.out.println("always stable: " + resultMergeSort.stable());


        //QuickSort
        System.out.println("Quick sort:");
        Comparator<MarkedValue<Integer>> markedComparatorQuickSort = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generatorQuickSort = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(n));

        SortingAlgorithm<MarkedValue<Integer>> algorithmQuickSort = new QuickSort<>(markedComparatorQuickSort, new QuickSort.PivotChooser() {
            @Override
            public int choosePivot(int left, int right) {
                return new Random().nextInt(right - left) + left;
                //return left;
            }
        });

        Result resultQuickSort = Tester.runNTimes(algorithmQuickSort,new LinkedListGenerator<>(generatorQuickSort), n, 20);

        printStatistic("time [ms]", resultQuickSort.averageTimeInMilliseconds(), resultQuickSort.timeStandardDeviation());
        printStatistic("comparisons", resultQuickSort.averageComparisons(), resultQuickSort.comparisonsStandardDeviation());
        printStatistic("swaps", resultQuickSort.averageSwaps(), resultQuickSort.swapsStandardDeviation());

        System.out.println("always sorted: " + resultQuickSort.sorted());
        System.out.println("always stable: " + resultQuickSort.stable());


    }

    private static void printStatistic(String label, double average, double stdDev) {
        System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
    }

    private static String double2String(double value) {
        return String.format("%.12f", value);
    }

}
