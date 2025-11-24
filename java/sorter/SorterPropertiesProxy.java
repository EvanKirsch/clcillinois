public class SorterPropertiesProxy implements ISorterProperties {

    // Public constructor to allow instantiation. This facilitates Dependency Injection
    public SorterPropertiesProxy() {

    }

    @Override
    public String getSorterImplemetion() {
        return SorterPropertiesSingleton.getInstance().getSorterImplemetion();
    }

}