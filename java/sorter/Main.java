import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SimpleSorterFactory factory = new SimpleSorterFactory();
        List<Integer> startingList = new ArrayList<>() {{
            add(2);
            add(4);
            add(10);
            add(3);
            add(1);
        }};

        ISorter sorter = factory.getSorter(args[0]);
        List<Integer> selectList = new ArrayList<>(startingList);
        sorter.sortList(selectList);

    }

}