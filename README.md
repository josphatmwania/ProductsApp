# Product App
## Overview
The Product App is a sample e-commerce application that provides product information such as names, prices, descriptions, images, and categories. The app is designed using the MVVM architecture and follows Clean Architecture principles. It integrates with the DummyJSON API to fetch product data and uses various libraries and tools to ensure high performance, testability, and maintainability.

The app is optimized for modern Android development using Jetpack Compose for the UI, and it includes both unit and UI tests for the network, database, and composable layers.

## Features
MVVM Architecture: Ensures separation of concerns, making the codebase more maintainable and scalable.
Clean Architecture: Promotes separation between data, domain, and presentation layers.
Network Integration: Fetches product data from the DummyJSON API using Retrofit and OkHttp.
Local Persistence: Uses Room database for offline storage.
Dependency Injection: Koin is used to handle dependency management.
Testing: Comprehensive tests for the network layer, database layer, and UI components using JUnit, MockK, MockWebServer, and Truth.
## Libraries Used
The following libraries are used in the app for development, testing, and dependency management:

## Core Libraries
Kotlin: Language used for development. 
1. Jetpack Compose: UI framework for building reactive UIs.
2. Room: Local persistence library for database handling. 
3. Retrofit: HTTP client for network requests. 
4. OkHttp: Used for making HTTP requests with logging. 
5. Koin: Dependency injection framework. 
6. Firebase Crashlytics: Integrated for real-time crash reporting and analytics. 
7. Navigation Compose: For managing navigation within the app. 
8. Testing Libraries 
9. JUnit: Unit testing framework. 
10. MockK: Mocking library for unit tests. 
11. MockWebServer: Mock server for testing network requests. 
12. Truth: Assertion library for testing.

### Architecture
The app follows Clean Architecture principles, dividing the codebase into the following layers:

- Data Layer:  ~ Contains the repository, data sources (local and remote), and API services.
* Domain Layer ~ Includes the business logic, use cases, and interfaces.
+ Presentation Layer: ~ Includes ViewModels and UI composables, built using Jetpack Compose.

### Setup and Installation

### Clone the repository:

`git clone <https://github.com/josphatmwania/ProductsApp>`
Open the project in Android Studio.

**Build and run the project.**

The app is integrated with Firebase; ensure you have the google-services.json file configured properly for Crashlytics and Analytics to work.

### API
The app consumes product data from the DummyJSON Products API to simulate an e-commerce store. The API provides details such as:

* Product name
* Price
* Description 
* Images 
* Category 
* Testing

**The app includes extensive tests across multiple layers:**

- Network Layer: Tested using MockWebServer and Retrofit. 
* Database Layer: Tested with Room and JUnit. 
+ UI Layer: Tested using Espresso and Jetpack Compose testing utilities.

#### To run the tests:


## Contributing
Feel free to fork the repository and submit pull requests. All contributions are welcome.

## License

This project is licensed under the [MIT License](LICENSE).
