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
        System.out.println(startingList);
        sorter.sortList(selectList);
        System.out.println(selectList);

    }

}