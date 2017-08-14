<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>driver</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>  
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css">
	
	<spring:url value="/resources/css/driver.css" var="drivercss" />
	<spring:url value="/resources/js/driver.js" var="driverjs" />
	<link href="${drivercss}" rel="stylesheet" />
	<script src ="${driverjs}" type="text/javascript"></script>
</head>
<body>
	
<!--Header-->
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
      <ul class="right hide-on-med-and-down">
        <li>
        	<a class="dropdown-user-info" href="#" data-activates='user_info'><i class="material-icons Medium">account_circle</i></a>
        </li>
      </ul>
	  <!-- User Detial Dropdoe -->
	  <ul id='user_info' class='dropdown-content'>
	  	<li>
        	<a id="notifications_icon" class="dropdown-icon" data-activates='notification'></a>
        </li>
	  </ul>
    </div>
  </nav>
<br>
<div class="container">
		<div class="row">
			<div id="my_schedule"></div>
			<h5 class="title">Shuttle Bus Information</h5>
				<br>
			<table class="bordered centered highlight">
			        <thead>
			          <tr>
			              <th>Date</th>
			              <th>Bus ID</th>
			              <th>Bus Driver</th>
			              <th>Destination</th>
			              <th>Total Seats</th>
			              <th>Customer</th>
			              <th>Staff</th>
			              <th>Student</th>
			              <th>Travel Time</th>
			              <th>Passenger</th>
			              <th>Confirm</th>     
			          </tr>
			        </thead>
			        <tbody id="getSchedule"></tbody>
     		 </table>
		</div>
	</div>
<!-- Modal Structure -->
<div id="user_detail" class="modal modal-fixed-footer">
		    <div class="modal-content">
		      <h5 class="center title">List of Passengers</h5>
		      <table class="bordered centered highlight">
		        <thead>
		          <tr>
		              <th>No.</th>
		              <th>UserID</th>
		              <th>Name</th>
		              <th>Batch</th>
		              <th>Role</th>
		              <th>Seat Number</th>
		          </tr>
		        </thead>
		        <tbody  id="getDetail"></tbody>
		      </table>
		    </div>
		    <div class="modal-footer">
		        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Agree</a>
		    </div>
	   </div>
<!--footer  -->
 <footer class="page-footer hide-on-med-and-down">
          <div class="container">
            <div class="row">
              <div class="col l6 s12">
                <h5 class="white-text">Footer Content</h5>
                <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
              </div>
              <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                  <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                  <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                  <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                  <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="footer-copyright">
            <div class="container">
            © 2014 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
          </div>
</footer>		

<script type="text/javascript">

</script>
</body>
</html>