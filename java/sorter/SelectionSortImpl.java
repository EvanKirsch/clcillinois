import java.util.List;

public class SelectionSortImpl implements ISorter {

    @Override
    public <T extends Comparable<T>> List<T> sortList(List<T> list) {
        System.out.println("Start Selection Sort Implementation");
        int i = 0;
        while (i < list.size()) {
            int j = i;
            int currentSelectedIndex = i;

            while (j < list.size()) {
                if(list.get(j).compareTo(list.get(currentSelectedIndex)) < 0) {
                    currentSelectedIndex = j;
                }

                j++;
            }

            T selectedValue = list.get(currentSelectedIndex);
            T currentValue = list.get(i);
            list.set(i, selectedValue);
            list.set(currentSelectedIndex, currentValue);

            System.out.println("  " + list);

            i++; 
        }
        System.out.println("End Selection Sort Implementation");
        return list;
    }

}
