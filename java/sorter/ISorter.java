import java.util.List;

public interface ISorter {

    <T extends Comparable<T>> List<T> sortList(List<T> list);

}
