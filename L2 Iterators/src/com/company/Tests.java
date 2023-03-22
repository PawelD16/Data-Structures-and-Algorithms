package com.company;

public class Tests {

    public Tests() {

        System.out.println("Standard situation:");
        SeriesIterator<Integer> integerSeriesIterator = new SeriesIterator<>(n -> 2 * n);
        Series<Integer> integerSeries = new FiniteSeries<>(integerSeriesIterator, 15);

        for (Integer integer : integerSeries) {
            System.out.println(integer);
        }


        System.out.println("\n\nAnother standard situation:");
        SeriesIterator<String> stringSeriesIterator = new SeriesIterator<>(new SeriesGenerator<String>() {
            @Override
            public String generate(int n) {
                String string = "";
                for (int i = 0; i < n; i++) string += 'a';
                return string;
            }
        });
        Series<String> stringSeries = new FiniteSeries<>(stringSeriesIterator, 5);

        for (String string : stringSeries) {
            System.out.println(string);
        }


        System.out.println("\n\nIf the iterator is null (should be no elements):");
        integerSeries = new FiniteSeries<>(null, 10);

        for (Integer string : integerSeries) {
            System.out.println(string);
        }


        System.out.println("\n\nIf the iterator is iterating over generated nulls, then we treat it like null is correct:");
        integerSeriesIterator = new SeriesIterator<>(new SeriesGenerator<Integer>() {
            @Override
            public Integer generate(int n) {
                return null;
            }
        });
        integerSeries = new FiniteSeries<>(integerSeriesIterator, 10);

        for (Integer string : integerSeries) {
            System.out.println(string);
        }


        System.out.println("\n\nIf the generator is null, it won't be allowed to generate anything (should be no elements made):");
        integerSeriesIterator = new SeriesIterator<>(null);
        integerSeries = new FiniteSeries<>(integerSeriesIterator, 10);

        for (Integer string : integerSeries) {
            System.out.println(string);
        }


        System.out.println("\n\nIf amount of elements in Series is equal to 0 (should be no elements made):");
        integerSeriesIterator = new SeriesIterator<>(n -> n * n);
        integerSeries = new FiniteSeries<>(integerSeriesIterator, 0);

        for (Integer string : integerSeries) {
            System.out.println(string);
        }


        System.out.println("\n\nIf amount of elements in Series is negative (should be no elements made):");
        integerSeriesIterator = new SeriesIterator<>(n -> 0);
        integerSeries = new FiniteSeries<>(integerSeriesIterator, 0);

        for (Integer integer : integerSeries) {
            System.out.println(integer);
        }

        integerSeriesIterator = new SeriesIterator<>(n -> 2*n);
        integerSeries = new Series<>(integerSeriesIterator);

        System.out.println("\n\nInfinite series: ");

        for(int i=0;i<5;i++){
            System.out.println(integerSeriesIterator.next());
        }
        for(int i=0;i<5;i++){
            System.out.println(integerSeriesIterator.next());
        }

        for (Integer integer : integerSeries) {
            System.out.println(integer);
        }

    }
}
