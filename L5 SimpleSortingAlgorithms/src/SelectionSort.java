import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SelectionSort<T> extends SortingAlgorithm<T> {

    public SelectionSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        for (int slot = 0; slot < size / 2; ++slot) {
            int smallest = slot, biggest = slot;
            T biggestElement = list.get(smallest);

            for (int check = slot; check <= size - slot - 1; ++check) {

                if (compare(list.get(check), list.get(smallest)) < 0)
                    smallest = check;

                else if (compare(list.get(check), list.get(biggest)) > 0) {
                    biggest = check;
                    biggestElement = list.get(biggest);
                }
            }

            swap(list, slot, smallest);

            if (list.get(smallest) == biggestElement) swap(list, size - slot - 1, smallest);
            else swap(list, size - slot - 1, biggest);
        }
        return list;
    }
}
