# WeatherApplication

All the functions in the assignment are done

The function of Application : it has 3 pages
1. Weather page showing fetched weather information, when the selected city gets changed, the weather will be updated. 
2. Gallery page showing 10 images fetched from internet and each of them is tappable. Animation is added when an image gets tapped.
3. Contact page showing 3 fields under difference verification rules, which will verify the field on they losing focus. the Submit button will only be clickable when all three fields are valid.

The design of Application
These 3 pages(fragments) are within one activity and is controlled by NavController

The application was developed under MVVM design pattern with minor difference.
For Weather page, it uses MVVM clean architecture which consists of View, View Model, Domain and Repository. 
For Gallery and Contact pages, they don't have too many use cases, so they don't have the Domain layer.


The TDD of Application
The test cases are not complete yet due to limited time. Two unit tests are done: ContactFormUnitTest and FetchWeatherUseCaseUnitTest, while WeatherModelTest hasn't been finished yet.

All the functions in the assignment have been tested manually.








