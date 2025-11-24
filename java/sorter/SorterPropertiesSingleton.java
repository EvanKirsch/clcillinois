import java.util.Properties;
import java.io.FileInputStream;

public class SorterPropertiesSingleton implements ISorterProperties {

    private static SorterPropertiesSingleton instance;
    private Properties properties;

    // private constrctor to prevent instatiation 
    private SorterPropertiesSingleton() {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propPath = rootPath + "Sorter.properties";
        properties = new Properties();

        try {
            properties.load(new FileInputStream(propPath));
        } catch (Exception e) {
            System.out.println("Failed to load sorter properties file form: " + propPath);
        }
    }

    // Only allow instantiation of class if the instance is null, otherwise return the current instance
    public static SorterPropertiesSingleton getInstance() {
        if (instance == null) {
            instance = new SorterPropertiesSingleton();
        }
        return instance;
    }

    @Override
    public String getSorterImplemetion() {
        return properties.getProperty("sorter.implemention");
    }
    
}