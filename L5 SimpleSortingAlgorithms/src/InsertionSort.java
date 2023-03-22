import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T> extends SortingAlgorithm<T> {

    public InsertionSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    private int binarySearch(List<T> list, int l, int r, T value) {

        while (l <= r) {
           int m = (l + r) / 2;

            if(list.get(m).equals(value)) return m;

            if (compare(value, list.get(m)) >= 0) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        for (int i = 1; i < list.size(); ++i) {
            T value = list.get(i);
            int k = binarySearch(list, 0, i, value);

            for (int j = i; j > k && k > -1; --j) {
                //list.set(j, list.get(j - 1));
                swap(list, j, j-1);
            }

            if (k > -1) {
                list.set(k, value);
                swap(list, 0,0); //liczenie zamian
            }
        }
        return list;
    }
}
