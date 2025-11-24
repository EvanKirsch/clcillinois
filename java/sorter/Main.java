import java.util.List;
import java.util.ArrayList;

public class Main {

    private final ISimpleSorterFactory factory;
    private final ISorterProperties sorterProperties;

    public Main(ISimpleSorterFactory factory, ISorterProperties sorterProperties) {
        this.factory = factory;
        this.sorterProperties = sorterProperties;
    }

    public static void main(String[] args) {
        // In our example we are using a main. Enterprise applications would have a framework running that would make 
        //   the dependency injection less intrusive and more valueable
        ISimpleSorterFactory factory = new SimpleSorterFactory();  // Specify the implementation of the ISimpleSorterFactory to use SimpleSorterFactory
        ISorterProperties sorterProperties = new SorterPropertiesProxy(); // using proxy to access singleton to allow for more control over the objects creation

        // Inject our dependencys into a Main implemention
        Main dependencyInjectedMain = new Main(factory, sorterProperties);
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

        String sorterImpl = sorterProperties.getSorterImplemetion();

        ISorter sorter = factory.getSorter(sorterImpl);
        sorter.sortList(list);

    }

}