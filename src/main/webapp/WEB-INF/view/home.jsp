<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Expedia Weather Service</title>
    <link href="resources/css/app.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="main-panel">
        <div class="panel-header">
            <h2>Expedia Weather Service</h2>
        </div> <!-- End of panel-header -->
        <div class="main-content">
            <div class="weather-form-panel">
                 <form:form action="getWeather" method="POST" commandName="weatherRequest">
                    <label for="searchByZip">Enter Zip Code: </label>
                    <form:input path="zipCode" />
                    <input type="submit"/><br/>
                    <form:errors path="zipCode" cssClass="error" />
               </form:form>
            </div> <!-- End of weather-form-panel -->
            <c:if test="${not empty weatherResponse}">
                <div class="weather-data-panel">
                    <table border="1">
                        <tr>
                            <th colspan="2" align="center">
                                <h2>Weather by ZIP Code</h2>
                            </th>
                        </tr>
                        <tr>
                            <td><label>Zip Code</label></td>
                            <td>${weatherRequest.zipCode}</td>
                        </tr>
                        <tr>
                            <td><label>State Name</label></td>
                            <td>${weatherResponse.current_observation.observation_location.state}</td>
                        </tr>
                        <tr>
                            <td><label>City Name</label></td>
                            <td>${weatherResponse.current_observation.observation_location.city}</td>
                        </tr>
                        <tr>
                            <td><label>Temperature (F)</label></td>
                            <td>${weatherResponse.current_observation.temp_f}</td>
                        </tr>
                    </table>
                </div> <!-- End of weather-data-panel -->
            </c:if>
        </div> <!-- End of main-content -->
    </div> <!-- End of main-panel -->
</body>
</html>