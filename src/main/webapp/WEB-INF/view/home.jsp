<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Weather Service</title>
    <link href="resources/css/app.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="main-panel">
        <div class="panel-header">
            <h2>Weather Service</h2>
        </div> <!-- End of panel-header -->
        <div class="main-content">
            <div class="weather-form-panel">
               <form class="weather-search-form" action="search">
                    <label for="searchByZip">Enter Zip Code: </label>
                    <input type="text" value="Search" />
                    <input type="button" value="Search" />
               </form>
            </div>
        </div> <!-- End of main-content -->
    </div> <!-- End of main-panel -->

    <!-- JS Includes -->
    <script src="resources/js/common/jquery-1.11.1.min.js"></script>
</body>
</html>