import java.util.List;

public interface ISorter {

    <T extends Comparable<T>> void sortList(List<T> list);

}
