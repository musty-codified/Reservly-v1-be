# Hotel Management System
The backend api for a Hotel Management System

## Technology ##
Following Frameworks and libraries were used during the development of API :
- **JDK: 17** Java version
- **Spring Boot: 2.76**
- **Build Tool: Maven**
- **MySQL**
- **Docker**
- **Memcached**

## Running the server locally ##
Build and package the Spring Boot project into a single executable Jar file using Maven,
You will need to run the Maven command from the project folder which contains the pom.xml file.
Ensure Memcached is installed and running on your machine before you run this service.

Using the following commands:

Compile the project.
```
maven clean compile
```
Package the application and generate the executable Jar file.
```
maven package
```
Run the Spring Boot application from a command line using the command below.
```
java -jar target/Reservly-v1-be-0.0.1-SNAPSHOT.jar.original
```

## API Documentation ##
The tool for API documentation used is Swagger, 
Access the endpoints from following url - [here](http://localhost:8080/swagger-ui/index.html#/)









