public interface ISimpleSorterFactory {

    /**
     * Example of the Factory Method design pattern. 
     *   By specifying the instantiation method of each implemenation,
     *   this factory is able to instantiate the implementing class as specified 
     *   without requiring any of the calling code to have knowedge of the implementation
     * 
     * @param strImpl the implementation to instantiate
     * @return an instaniated implementation of ISorter
     */
    ISorter getSorter(String strImpl);

}