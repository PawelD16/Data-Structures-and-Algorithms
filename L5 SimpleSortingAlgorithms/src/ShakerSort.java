import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class ShakerSort<T> extends SortingAlgorithm<T> {

    public ShakerSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        for (int pass = 1; pass < size; ++pass) {

            if (pass % 2 == 1) {
                for (int left = pass / 2; left < (size - pass / 2 - 1); ++left) {
                    int right = left + 1;

                    if (compare(list.get(left), list.get(right)) > 0) {
                        swap(list, left, right);
                    }
                }

            } else {
                for (int right = (size - pass / 2 - 1); right >= (pass / 2); --right) {
                    int left = right - 1;

                    if (compare(list.get(left), list.get(right)) > 0) {
                        swap(list, left, right);
                    }
                }
            }
        }
        return list;
    }
}
