ExpediaWeatherApp
===================

This is Spring3 based Web Application that displays weather information based on the US Zip Codes.

## Project setup & configuration:

* Please ensure that maven is already configured.

* Download the ExpediaWeatherApp repository and Navigate to ExpediaWeatherApp folder

## Testing

Please execute the below command to run independent unit test cases.
```sh
mvn clean test
```

## Running Weather Application

* Please execute below command to run weather application:

```sh
mvn clean install tomcat:run-war
```
* After Maven execution, run to application using below URL:
**[http://localhost:8080/weather](http://localhost:8080/weather)**

* Please key-in some valid US Zip codes and test