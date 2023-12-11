# Portfolio project IDATA1003 - 2023

STUDENT NAME = "Conrad Tinius Osvik"  
STUDENT ID = "590789"

## Project description
This is a portfolio project for the course IDATT1003 at NTNU. The project is a simple program that handles train departures in a registry where the user can query the registry by different parameters

## Project structure
The project has the following structure:
* `src/main/java` - contains the main program
    * `src/main/java/commands` - contains the commands the user can execute
    * `src/main/java/controllers` - contains the controller of the application
    * `src/main/java/exceptions` - contains the custom user input exceptions
    * `src/main/java/input` - contains the validated user input
    * `src/main/java/models` - contains the models of the data in the application
    * `src/main/java/utils` - contains utility classes
    * `src/main/java/views` - contains the console view used in the application
* `src/test/java` - contains the tests
    * `src/test/java/controllers` - contains the tests for the controller
    * `src/test/java/input` - contains the tests for the user input
    * `src/test/java/models` - contains the tests for the models
    * `src/test/java/utils` - contains the tests for the utility classes

each of these packages has a purpose and should only focus on that purpose
## Link to repository
https://github.com/ConradOsvik/TrainDispatchSystem

## How to run the project
What is the input and output of the program? What is the expected behaviour of the program?'
To run the project, execute the `main` method in the `TrainDispatchApp` class. This serves as the entry point of the application and triggers its start-up. Upon launch, the application presents a terminal-based menu, offering the user a choice of nine operations. The application operates in a continuous loop, refreshing the display menu after each operation is completed. The application will cease to run when the user opts to exit by selecting the ninth command. This action effectively terminates the application.

## How to run the tests
To run the tests:
* execute `mvn test` in the terminal at the root of the project. This will run all the tests in the project.
* If you want to run a clean test, execute `mvn clean test` in the terminal at the root of the project. This will run all the tests in the project after cleaning the target folder.
* Alternatively, you can execute `mvn -Dtest=_TestClassName_ test` to run a singular test.

## References
