import java.util.List;

public interface ISorter {

    /**
     * Sorts list and prints state of list throughout sorting.
     *   This is a good example of how you can use an interface and have 
     *   multiple underling implementations
     * 
     * @param <T> Generic list type
     * @param list returned by refrence - list to sort 
     */
    <T extends Comparable<T>> void sortList(List<T> list);

}
