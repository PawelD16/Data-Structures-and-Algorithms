
import java.util.Comparator;

import core.SortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

    public static void main(String[] args) {
        int q = 10;

        //Bubble sort
        System.out.println("Normal bubble sort:");
        Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(100));

        SortingAlgorithm<MarkedValue<Integer>> algorithm = new BubbleSort<MarkedValue<Integer>>(markedComparator);

        Result result = Tester.runNTimes(algorithm, generator, q, 20);

        printStatistic("time [ms]", result.averageTimeInMilliseconds(), result.timeStandardDeviation());
        printStatistic("comparisons", result.averageComparisons(), result.comparisonsStandardDeviation());
        printStatistic("swaps", result.averageSwaps(), result.swapsStandardDeviation());

        System.out.println("always sorted: " + result.sorted());
        System.out.println("always stable: " + result.stable());


        //Shaker sort
        System.out.println("\n\nShaker sort:");
        Comparator<MarkedValue<Integer>> markedComparatorShakerSort = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generatorShakerSort = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(100));

        SortingAlgorithm<MarkedValue<Integer>> algorithmShakerSort = new ShakerSort<MarkedValue<Integer>>(markedComparatorShakerSort);

        Result resultShakerSort = Tester.runNTimes(algorithmShakerSort, generatorShakerSort, q, 20);

        printStatistic("time [ms]", resultShakerSort.averageTimeInMilliseconds(), resultShakerSort.timeStandardDeviation());
        printStatistic("comparisons", resultShakerSort.averageComparisons(), resultShakerSort.comparisonsStandardDeviation());
        printStatistic("swaps", resultShakerSort.averageSwaps(), resultShakerSort.swapsStandardDeviation());

        System.out.println("always sorted: " + resultShakerSort.sorted());
        System.out.println("always stable: " + resultShakerSort.stable());


        //Selection sort
        System.out.println("\n\nSelection sort:");
        Comparator<MarkedValue<Integer>> markedComparatorSelectionSort = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generatorSelectionSort = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(100));

        SortingAlgorithm<MarkedValue<Integer>> algorithmSelectionSort = new SelectionSort<>(markedComparatorSelectionSort);

        Result resultSelectionSort = Tester.runNTimes(algorithmSelectionSort, generatorSelectionSort, q, 20);

        printStatistic("time [ms]", resultSelectionSort.averageTimeInMilliseconds(), resultSelectionSort.timeStandardDeviation());
        printStatistic("comparisons", resultSelectionSort.averageComparisons(), resultSelectionSort.comparisonsStandardDeviation());
        printStatistic("swaps", resultSelectionSort.averageSwaps(), resultSelectionSort.swapsStandardDeviation());

        System.out.println("always sorted: " + resultSelectionSort.sorted());
        System.out.println("always stable: " + resultSelectionSort.stable());


        //Insertion sort
        System.out.println("\n\nInsertion sort:");
        Comparator<MarkedValue<Integer>> markedComparatorInsertionSort = new MarkedValueComparator<Integer>(new IntegerComparator());

        Generator<MarkedValue<Integer>> generatorInsertionSort = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(100));

        SortingAlgorithm<MarkedValue<Integer>> algorithmInsertionSort = new InsertionSort<>(markedComparatorInsertionSort);

        Result resultInsertionSort = Tester.runNTimes(algorithmInsertionSort, generatorInsertionSort, q, 20);

        printStatistic("time [ms]", resultInsertionSort.averageTimeInMilliseconds(), resultInsertionSort.timeStandardDeviation());
        printStatistic("comparisons", resultInsertionSort.averageComparisons(), resultInsertionSort.comparisonsStandardDeviation());
        printStatistic("swaps", resultInsertionSort.averageSwaps(), resultInsertionSort.swapsStandardDeviation());

        System.out.println("always sorted: " + resultInsertionSort.sorted());
        System.out.println("always stable: " + resultInsertionSort.stable());

    }

    private static void printStatistic(String label, double average, double stdDev) {
        System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
    }

    private static String double2String(double value) {
        return String.format("%.12f", value);
    }

}
