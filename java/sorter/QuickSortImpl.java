import java.util.List;

public class QuickSortImpl implements ISorter {

    @Override
    public <T extends Comparable<T>> List<T> sortList(List<T> list) {
        System.out.println("Start Quick Sort Implementation");
        List<T> foundList = quickSortRecursive(list, 0, list.size()-1);
        System.out.println("End Quick Sort Implementation");
        return foundList;
    }

    private <T extends Comparable<T>> List<T> quickSortRecursive(List<T> list, int left, int right) {
        System.out.println("  " + list);
        if(left < right) {
            int splitLocation = partition(list, left, right);
            quickSortRecursive(list, left, splitLocation - 1);
            quickSortRecursive(list, splitLocation + 1, right);
        }
        return list;
    }

    private <T extends Comparable<T>> int partition(List<T> list, int left, int right) {
        T pivotValue = list.get(right);
        int lowIndex = left - 1;
        for (int i = left; i < right; i++) {
            if (list.get(i).compareTo(pivotValue) < 0) {
                lowIndex++;
                T lowValue = list.get(lowIndex);
                T highValue = list.get(i);
                list.set(i, lowValue);
                list.set(lowIndex, highValue);
            }
        }
        
        lowIndex++;
        pivotValue = list.get(right);
        T lowValue = list.get(lowIndex);
        list.set(lowIndex, pivotValue);
        list.set(right, lowValue);
        return lowIndex;
    }

}