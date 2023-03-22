import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class QuickSort<T> extends SortingAlgorithm<T> {
    PivotChooser pivotChooser;

    public QuickSort(Comparator<? super T> comparator, PivotChooser pivotChooser) {
        super(comparator);
        this.pivotChooser = pivotChooser;
    }

    @Override
    public List<T> sort(List<T> list) {
        this.quickSort(list, 0, list.size() - 1);
        return list;
    }

    public List<T> quickSort(List<T> list, int left, int right) {
        if (left < right) {
            int pivot = partition(list, left, right);

            quickSort(list, left, pivot - 1);
            quickSort(list, pivot + 1, right);
        }
        return list;
    }

    private int partition(List<T> list, int left, int right) {

        int pivot = pivotChooser.choosePivot(left, right);
        ListIterator<T> iterator = list.listIterator(left);
        ListIterator<T> iIterator = list.listIterator(left);
        ListIterator<T> rightIterator = list.listIterator(right);

        swap(list, right, pivot);
        T pivotValue = list.get(right);
        T temp = iterator.next();
        T iTemp = iIterator.next();

        int i = left - 1;
        while (left < right) {

            if (compare(temp, pivotValue) <= 0) {
                iterator.set(iTemp);
                iIterator.set(temp);
                iTemp = iIterator.next();
                i++;
                swap();
            }
            left++;
            temp = iterator.next();
        }

        iIterator.set(rightIterator.next());
        rightIterator.set(iTemp);
        swap();
        //swap(list, i + 1, right);
        return ++i;
    }

    public abstract static class PivotChooser {
        public abstract int choosePivot(int left, int right);
    }

}
