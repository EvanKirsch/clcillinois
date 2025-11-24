# Sorter
  This directory contains a set of java files to illustrate common industry design patterns and SOLID principles. We can see in the sorter example how powerful good design is as we are able to easily change the implemtion by simply updating the configuration file without the need to rebuild the application.

#### Key Takeaways:
  1. Use interfaces to abstract away need for knowedge of spacific implemention details and allow for swapping implementions without impacting the application
  1. Write small classes, a class should only have one job
  1. Design and composition can faclitate extensibility 

## Design Pattern Examples:
### Factory Method Pattern - `SimpleSorterFactory.java`
  This is an implemention of the "Factory Method" pattern, one method is called and an instatiated implemention is returned. The calling code has no knowedge of the implemetion used, but rather simply interacts with the iterface. This allows us to do some awesome things - like substatially alter the programs functionality without re-building the application.

### Singleton - `SorterPropertiesSingleton.java`
  The singleton design pattern is used prolifically in many enterprise systems. Here it is used in a rather mundane way to ensure all access to the properties file go through a single object. While in this example it may seem overkill - at scale this design pattern ensures cordnation accross the system.

  However, the singleton design pattern is not without fault. The pattern iteslf violates the Single Resposiblity Principle as the class is both resposible for controlling its own instantiation and loading properties. This could become problamatic for us in our sorter example if we were to write test for the `Main.main()` method - would want to be able to inject our own implemation of the `SorterPropertiesSingleton` to segment our testing.

## SOLID Priciples:
- S - Single Resposibility Principle
  - Programs should do one thing and do it well
  - In the sorter example - we can see that we have objects that are either resposible for creating objects (`SimpleSorterFactory.java`), or implementing logic (`MergeSortImpl.java`, `QuickSortImpl.java`, `SelectionSortImpl.java`). In School its very common to have objects with mixed resposibility. This is fine in a limited scope, however in large applications it is nessassary to strictly adhear to this principle
- O - Open/Closed Principle
  - Make programs extendedable instead of changeable 
  - In our example - we could extend the program by adding additional implementations of the interface. 
- L - Liskov Substution Principle
  - You can use subclasses without breaking a program
  - In the sorter example - we can use any of the Interface's implementing methods wihout modifiying the result of the sorting
- I - Interface Segregation Principle
  - Make interfaces as small as possible
  - In the sorter example - our Main program only needs to use `.sort` and this is the only method avilable in the interface
- D - Dependency Injection Principle
  - Specify dependencies in the constructor
  - In the sorter Example - The Main class is DIed, it looks kind of wierd (and isn't the best) because we are also instaniating the objects in the class before we construct it. In an enterprise application with a server running, this would be much more valueable as the applicaiton server would be able to satisfy the dependencies of the class without a spacific implemention.