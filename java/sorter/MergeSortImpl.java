import java.util.List;

public class MergeSortImpl implements ISorter {

    // TODO - implememnt

    @Override
    public <T extends Comparable<T>> void sortList(List<T> list) {
        System.out.println("Start Merge Sort Implemention");
        if(list.size() == 0) {
            return;
        }

        int splitIndex = Math.round(list.size() / 2);
        List<T> leftList = list.subList(0, splitIndex);
        List<T> rightList = list.subList(splitIndex, list.size());

        sortList(leftList);
        sortList(rightList);

        return;
    }

}
