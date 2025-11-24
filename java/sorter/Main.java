import java.util.List;
import java.util.ArrayList;

public class Main {

    private final ISimpleSorterFactory factory;
    private final SorterPropertiesSingleton sps;

    public Main(ISimpleSorterFactory factory, SorterPropertiesSingleton sps) {
        this.factory = factory;
        this.sps = sps;
    }

    public static void main(String[] args) {
        // In our example we are using a main. Enterprise applications would have a framework running that would make 
        //   the dependency injection less intrusive and more valueable
        ISimpleSorterFactory factory = new SimpleSorterFactory();  // Specify the implementation of the ISimpleSorterFactory to use SimpleSorterFactory
        SorterPropertiesSingleton sps = SorterPropertiesSingleton.getInstance(); // this is still pretty tightly coupled. Wrapping in an instaniable proxy is a common pattern to avoid this issue

        // Inject our dependencys into a Main implemention
        Main dependencyInjectedMain = new Main(factory, sps);
        dependencyInjectedMain.doMain();
    }

    // Notice how rather than instaniating a concreate implementation of the SimpleSorterFactory or SorterPropertiesSingleton 
    //   we are injecting our implementions in the instaniation of the object 
    public void doMain() {
        List<Integer> list = new ArrayList<>() {{
            add(2);
            add(101);
            add(11);
            add(4);
            add(10);
            add(3);
            add(1);
        }};

        String sorterImpl = sps.getSorterImplemetion();

        ISorter sorter = factory.getSorter(sorterImpl);
        sorter.sortList(list);

    }

}