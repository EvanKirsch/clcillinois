import java.util.List;
import java.util.ArrayList;

public class MergeSortImpl implements ISorter {


    @Override
    public <T extends Comparable<T>> void sortList(List<T> list) {
        System.out.println("Start Merge Sort Implemention");
        mergeSort(list);
        System.out.println("End Merge Sort Implemention");
    }

    public <T extends Comparable<T>> void mergeSort(List<T> list) {
        if(list.size() == 1) {
            return;
        }

        int splitIndex = Math.round(list.size() / 2);
        List<T> leftList = new ArrayList<>(list.subList(0, splitIndex));
        List<T> rightList = new ArrayList<>(list.subList(splitIndex, list.size()));

        mergeSort(leftList);
        mergeSort(rightList);

        int rightIndex = 0;
        int leftIndex = 0;
        int sortIndex = 0;

        while(rightIndex < rightList.size() && leftIndex < leftList.size()) {
            if(leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) < 0) {
                list.set(sortIndex, leftList.get(leftIndex));
                leftIndex++;

            } else {
                list.set(sortIndex, rightList.get(rightIndex));
                rightIndex++;

            }
            sortIndex++;

        }

        while (leftIndex < leftList.size()) {
            list.set(sortIndex, leftList.get(leftIndex));
            leftIndex++;
            sortIndex++;
            
        }

        while (rightIndex < rightList.size()) {
            list.set(sortIndex, rightList.get(rightIndex));
            rightIndex++;
            sortIndex++;
            
        }
        System.out.println(list);

        return;
    }

}