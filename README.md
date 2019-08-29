# This code contains
1. A simple Apache Camel route that makes a GET web request to  http://api.postcodes.io/. 
It takes input parameters from src/data/input and saves the output into src/data/output. 
The Camel route will be called through the main function MySpringBootApplication.java 
(as described below there are two options to activate the Camel route)

2. Two test functions using Hoverfly on JUnit running on CaptureOrSimulation mode to mock a GET 
web request to http://api.postcodes.io/.
The first test uses a rest-template, the second test activates the Camel route through the main 
function 'MySpringBootRouter.java'.

# Problem: 
In the main function 'MySpringBootApplication.java' you will find two options on how to activate the route.
- Option 1 works perfectly fine for both tests (the Camel Route JUnit Test and rest template JUnit Test) 
- Option 2 does not work for JUnit Test of the Camel Route.


# How to run from command line:

mvn install && mvn compile exec:java -Dexec.mainClass="com.example.MySpringBootApplication"
