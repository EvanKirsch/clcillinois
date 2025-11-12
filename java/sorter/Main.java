public class Main {

    public static void main(String[] args) {

        SimpleSorterFactory factory = new SimpleSorterFactory();
        ISorter mySorter = factory.getSorter(SorterType.SELECTION_SORT);
        // TODO - implement
        mySorter.sortList(null);

    }

}