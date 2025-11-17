import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SimpleSorterFactory factory = new SimpleSorterFactory();
        ISorter sorter = factory.getSorter(SorterType.SELECTION_SORT);
        List<Integer> startingList = new ArrayList<>() {{
            add(2);
            add(4);
            add(10);
            add(3);
            add(1);
        }};

        List<Integer> selectList = new ArrayList<>(startingList);
        sorter.sortList(selectList);

        sorter = factory.getSorter(SorterType.QUICK_SORT);

        List<Integer> quickList = new ArrayList<>(startingList);
        sorter.sortList(quickList);

        sorter = factory.getSorter(SorterType.MERGE_SORT);
        List<Integer> mergeList = new ArrayList<>(startingList);
        sorter.sortList(mergeList);

    }

}