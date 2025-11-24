public class SimpleSorterFactory implements ISimpleSorterFactory {

    @Override
    public ISorter getSorter(String strImpl) {
        ISorter mySorter;
        SorterType impl = SorterType.valueOf(strImpl.toUpperCase());
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
