package com.company;

public class IterFib implements IteratorGamma<Integer> {

    private int last, next, current, max;

    public IterFib(int max) {
        this.max = max;
        last = 0;
        current = 1;
        next = 1;
    }

    @Override
    public void first() {
        last = 0;
        current = 1;
        next = 1;
    }

    @Override
    public void last() {
        int i = 0;
        while (next < max) {
            current = next;
            next = fib(i);
            i++;
        }
        last = next - current;
    }

    @Override
    public void next() {
        if (!isDone()) {
            last = current;
            current = next;
            next = current + last;
        }
    }

    @Override
    public void previous() {
        if (!isDone() && current > 1){
            next = current;
            current = last;
            last = next - current;
        }
    }

    @Override
    public boolean isDone() {
        if (next > max || last < 0) return true;
        return false;
    }

    @Override
    public Integer current() {
        return current;
    }

    private int fib(int n) {
        if (n <= 1)
            return n;
        return fib(n - 1) + fib(n - 2);
    }
}
