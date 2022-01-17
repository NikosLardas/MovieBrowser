Design Patterns are templates paired with a set of rules about how the code for an application or a functionality that will be developed should be organized, implemented and structured. Following a robust Design Pattern that fits the needs of the application, and can also be adopted smoothly by the development team, is a great way to create understandable, reusable, cleaner, scalable and maintainable code. Two of the most popular and adopted Design Patterns for Android Mobile Application Development are the MVC and the MVP Design Patterns, both of which will be analyzed and compared further on.

- MVC

The MVC Design Pattern stands for Model-View-Controller. Similar to other the Design Patterns that include a Model and a View, in MVC the User is the basis of the System. 

The MVC Design Pattern suggests splitting the code into 3 different parts. Each class or file that is created should be categorized in one of the 3 suggested layers:

Model: The Model is responsible for defining the business and data model and storing all of the application data, as well as handling the domain logic and performing CRUD operations with the database. It also includes business rules about the ways data can be manipulated.

View: The View represents all of the UI components and is responsible for everything connected with the UI of the application, which includes the representation of the Model Data to the User, as well as providing the ability for the user to interact with the application.

Controller: The controller provides all the business logic of the application and act as a connection point between the Model and the View. When a User performs an action in the View, the Controller will execute the corresponding business logic, update the data in the Model and directly or indirectly update the View so that the results of the user action appear in the UI.

- MVP

Another popular and widely adopted Design Pattern is the MVP pattern, which is a derivation of the MVC pattern and that stands for Model-View-Presenter. In MVP the Model and the View fulfill the same roles as in the MVC Design Pattern, with some key differences. In MVP the Model and the View are decoupled and do not communicate directly with each other. All communication passes through the Presenter, who is responsible for manipulating the Model as well as the View.

The Presenter performs all the business logic of the application, same as an MVC controller, establishes a two-way communication with the View and the Model and is responsible for updating both of them as a result of user actions, acting as a sort of “Middle-Man”. In this Design Pattern the View assumes a more passive role, handing out the task of deciding what happens to the View upon user interaction to the Presenter.

- MVC VS MVP

Both the MVC and MVP Design Patterns are widely adopted by Android developers and each of them comes with certain pros and cons.

The MVC Design Pattern is relatively easy to implement and does not require any additional libraries for it to work. Additionally, model classes in MVC are reusable without the need of further modification. On the other hand, because there is a tight coupling between the Controller and the View, if we apply any changes to the View, the Controller needs to be changed as well. Another major issue with MVC is that due to the way the Controller works, it’s difficult to do unit testing in applications developed using the MVC pattern.

In MVP this issue is resolved, since the Presenter does not make use of any Android API’s, which makes it much easier to test. On the other hand, MVP has the problem of maintenance because over time the amount of code stored in the Presenter increases greatly, which makes it difficult to maintain.

- MVC Design Pattern Implementation for the MovieApp

For the MovieApp Project the MVC Design Pattern will be implemented as it fits well with the current project design and structure and will be relatively easy and efficient for the development team to follow its rules and best practices. To Implement the MVC Design Pattern in the MovieApp Project, first of all a list of rules that will be followed should be compiled:

•	The Model should represent the application data and provide access to them, but do nothing else

•	The Model will contain specific sets of properties to represent the application data

•	The View will only contain presentational code, namely XML

•	Any User interaction with the View will call methods in our Controller

•	The Controller will contain all the logic of the application

•	The Controller will create Model instances and manage their lifecycles

•	All Activities and RecyclerViewAdapters will have the role of the Controller in the application

•	The Camel Case variable naming convention will be used throughout the application

•	All application logic that belongs to the Controller will be contained in the postCreate lifecycle method in the Activities, as additional custom methods within an Activity class or in RecyclerViewAdapter classes

Any further extension, modification, maintenance and support of the application should abide by these rules and follow the same approach that will be discussed further.

- UI/Design

For the MovieApp UI/Design the View will be defined as the series of Layout XML files contained in the resources/layouts package that currently includes the activity_main_movies.xml, activity_movie_details.xml, holder_movie_item.xml and holder_cast_item.xml. These files will only implement the user interface and in the case of user interaction, they will call Activity and RecyclerView methods to display the results. Any further additions and extensions to the application View should be XML files and they must be added to the Layout package.

- Implementation

The Model of the MovieApp will be defined as the already existing network package that will be renamed as datamodel and all the packages and classes it includes, namely the four JsonResponse classes which have the role of modeling the Data that we received from the MovieDB API and making them accessible to the rest of the application. Any further additions and extensions to the application Model should follow the same pattern as the existing classes and they must be added to the datamodel package.

The Controller of the MovieApp will be defined as the two Activity classes named MainActivity and MovieDetailsActivity. They contain all the business logic of the application, the API requests that are represented with the help of the Model, all the methods that provide the results corresponding to user actions and the methods used to change the UI displayed to the user. Any further additions and extensions to the application Controller should be Activity or RecyclerView files that follow the same structure as the already existing Activities and RecyclerViewAdapters.

Last but not least, the two RecyclerViews used in the application, namely the MoviesRecyclerView and the CastRecyclerView will also abide by the MVC Design Pattern with the Model being the JsonResponse data classes that are passed to the each RecyclerView, the View being the combination of the RecyclerView widget defined in the Layouts combined with the holder_movie_item.xml and holder_cast_item.xml files and both RecyclerViewAdapters being part of the application Controller as they contain the logic for how to display the data model in the views, manage the recycling, updating etc. Any further RecyclerViews that will be added in the application should follow the same pattern and structure that is already defined.
