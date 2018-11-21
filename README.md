# spark-multi-test
You might have been using [Spark](http://sparkjava.com/) and wondering how you might run your tests if they are in separate files. One solution
is presented in this repository!

I hope you find this mini-project useful.

## One solution
In this repository we have the following acceptance tests for our API:
* [AT_GetAllProgrammers](./src/test/java/upsd/acceptance/AT_GetAllProgrammers.java)
* [AT_GetProgrammerById](./src/test/java/upsd/acceptance/AT_GetProgrammerById.java)

Instead of running these files individually, we have added the following to `build.gradle`:
```
test {
    println 'Running acceptance and unit tests'
    include '*/TestSuite.class'
    include '*/controllers/*'
    include '*/repositories/*'
    testLogging {
        events "passed", "skipped", "failed", "standardOut", "standardError"
    }
}
```

Instead of running the acceptance tests individually, we first run the 
[test suite](./src/test/java/upsd/TestSuite.java), which is in charge of starting the server and setting up our 
[Helper](./src/test/java/upsd/helpers/Helper.java) class.

Within our test suite class, we add the following JUnit Suite runner, which informs JUnit of the classes that belong to
this suite (in our case, the acceptance tests):
```java
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AT_GetProgrammerById.class,
        AT_GetAllProgrammers.class
})
public class TestSuite {
```

## Running the tests
After reading the above I am sure you are eager to run the tests and see it in action. In order to do this, execute the
below in a terminal at the root of this project:

    $ ./gradlew test