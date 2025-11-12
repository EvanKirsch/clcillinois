public class SimpleSorterFactory {

    ISorter getSorter(SorterType impl) {
        ISorter mySorter;
        if(SorterType.MERGE_SORT.equals(impl)) {
            mySorter = new MergeSortImpl();

        } else if (SorterType.QUICK_SORT.equals(impl)) {
            mySorter = new QuickSortImpl();

        } else if (SorterType.SELECTION_SORT.equals(impl)) {
            mySorter = new SelectionSortImpl();

        } else {
            mySorter = new SelectionSortImpl();

        }

        return mySorter;
    }

}
