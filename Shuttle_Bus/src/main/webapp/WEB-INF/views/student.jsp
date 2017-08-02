<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Student</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
  
  <spring:url value="/resources/css/student.css" var="studentcss" />
  <link href="${studentcss}" rel="stylesheet" />
  
</head>
<body>
 <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>
      <ul class="right hide-on-med-and-down">
      	<li>
        	<a class="dropdown-icon" href="#" data-activates='request'><i class="large material-icons">dialpad</i></a>
        </li>	
        <li>
        	<a class="dropdown-icon" href="#" data-activates='request'><i class="material-icons">notifications</i></a>
        </li>
        <li>
        	<a href="#" class="dropdown-user-info" href="#" data-activates='user_info'><i class="material-icons Medium">account_circle</i></a>
        </li>
      </ul>
      	<ul id='request' class='dropdown-content'>
	    <li><a href="#emergencyForm">Emergency Booking</a></li><li class="divider"></li>
	    <li><a href="#exchangeSeat">Exchange Seat</a></li><li class="divider"></li>
	    <li><a id="req_donate" href="#donateTicket">Donate Ticket</a></li><li class="divider"></li>
	    <li><a href="#donateTicket">Permission</a></li><li class="divider"></li>
	  </ul>
	  

	  <ul id='user_info' class='dropdown-content'>
		  	<li><a href="#!">User Name</a></li><li class="divider"></li>
		    <li><a href="#!">No. of Ticket:&nbsp &nbsp<span>10</span></a></li><li class="divider"></li>
		    <li><a href="#!">Log Out</a></li><li class="divider"></li>
	  </ul>
	  <ul id="nav-mobile" class="side-nav">
	  		<li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">account_circle</i>Name: Mai Mom</a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">loyalty</i>No. of Ticket:&nbsp &nbsp<span>10</span></a></li>
		    <li><div class="divider"></div></li>
		    <li><a href="mobileSchedule"><i class="material-icons">event</i>Schedule Up to Date</a></li>
		    <li><div class="divider"></div></li>
		    <!-- 
		    	<li><a href="#!"><i class="material-icons">assignment_late</i>Emergency Booking</a></li>
			    <li><div class="divider"></div></li>
			    <li><a href="#!"><i class="material-icons">cached</i>Donate Ticket</a></li>
			    <li><div class="divider"></div></li>
			    <li><a href="#!"><i class="material-icons">settings</i>Setting</a></li>
		     -->
	  </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
 <!-- Request Form Modals Not Yet-->
  <div>
  	<div id="emergencyForm" class="modal modal-fixed-footer modal_request">
		    <div class="modal-content">
		      <h4>Modal Header</h4>
		      <p>A bunch of text</p>
		    </div>
		    <div class="modal-footer">
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Request</a>
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Cancel</a>
		    </div>
		</div>
		<div id="exchangeSeat" class="modal modal-fixed-footer modal_request">
		    <div class="modal-content">
		      <h4>Modal Header</h4>
		      <p>A bunch of text</p>
		    </div>
		    <div class="modal-footer">
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Exchange</a>
		      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Cancel</a>
		    </div>
		</div>
		<div id="donateTicket" class="modal modal-fixed-footer modal_request">
		    <div class="modal-content">
		      <h5 class="center text-orange">Donate Ticket</h5>
		      <br>
		      <div class="row">
			     <form class="col l12">
				      Donate Tickets To:
			          <div class="input-field inline right donate_select">
			            <select id="donate_batch">
					    </select>
			            <select id="danate_to_username">
					    </select>
			          </div>
			          <br><br><br><br><br>
			          Number of Tickets:
			          <div class="input-field inline right donate_select">
			            <input id="no_ticket" type="number" class="validate input_border">
			          </div>
			    </form>
			  </div>
		    </div>
		    <div class="modal-footer"> 
		      <a id="donate_btn" class="modal-action modal-close waves-effect waves-green btn-flat ">Donate</a>
		      <a class="modal-action modal-close waves-effect waves-green btn-flat ">Cancel</a>
		    </div>
		</div>
  </div>
<div class="container hide-on-med-and-down">
  	<br>
      <h5 class="header center orange-text"><b>vKirirom Shuttle Bus System</b></h5>
      <p class="header col s12 light center">A modern responsive front-end framework based on Material Design</p>
</div>
<div id="session" class="section hide-on-med-and-down">    
      <div class="light-blue lighten-1">
      		<br><br>
      		<div class="container">
      		   <form id="SelectRadio">
				    <span>
				    	<input class="with-gap" name="option_way" type="radio" id="roundWay" value="RoundWay" checked="checked"/>
				        <label class="option_label" for="roundWay">Round Ways</label>
				    </span>
				    <span>
				    	<input class="with-gap" name="option_way" type="radio" id="oneWay" value="OneWay"/>
				        <label class="option_label" for="oneWay">One Way</label>
				    </span>	  
				</form>
				<br>
		      <div class="row row_book">
		        <div class="col s12 l3 custom_input">
			   		<div id="getSelectFrom" class="input-field s6"></div>
			   	</div>
		        <div class="col s12 l3 custom_input">
			   		<div id="getSelectTo" class="input-field s6">      
				      <select class="validate">
				         <option value="" disabled selected>Return</option>
				      </select>
				    </div>
			   	</div>
			   	<div class="col s12 l3">
			   		<div class="input-field s6 flatpickr">
				   		<input type="text" placeholder="Select Date" id="check_in_date" data-input class="input flatpickr-input active"> 
					</div>
			   	</div>
			   	<div class="col s12 l3 custom_book light-blue lighten-1">
			   		<a class="btn orange lighten-1 bookingBtn" id="book_now">Book Now</a>   			
			   	</div>
		      </div>
		    </div>
		    <br><br>
      </div>
</div>
<div class=" container hide-on-large-only">
      <div class="light-white lighten-1 hide-on-large-only r_book">
      		<div class="row container">
      			<br>
      			<form id="SelectRadio">
				    <span>
				    	<input class="with-gap" name="mobile-way" type="radio" id="m_roundWay" value="RoundWay" checked="checked"/>
				        <label class="option_label" for="m_roundWay">Round Ways &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</label>
				    </span>
				    <span>
				    	<input class="with-gap" name="mobile-way" type="radio" id="m_oneWay" value="OneWay"/>
				        <label class="option_label" for="m_oneWay">One Way</label>
				    </span>	  
				</form>
				<br>
			</div>
      		<div class="row row_mobileBook">
		        <div class="col s12">
			   		<div id="m_getSelectFrom" class="input-field s6 light-white lighten-1"></div>
			   		<br>
			   	</div>
			</div>
			<div id="m_getSelectTo" class="row row_mobileBook">
		        <div class="col s12">
			   		<div class="input-field s6 ">      
				      <select class="validate light-white lighten-1">
				        <option value="" disabled selected>Return</option>
				      </select>
				    </div>
				    <br>
			   	</div>
			</div>
			<div class="row row_mobileBook">
		        <div class="col s12 l3">
			   		<div class="input-field s6 flatpickr">
				   		<input id="m_check_in_date" type="text" placeholder="Select Date" data-input class="input flatpickr-input active"> 
					</div>
					<br>
			   	</div>
			</div>
      </div>
      <div class="row r_book">
		   <div class="col s12 l3">
			   	<a class="btn orange lighten-1 bookingBtn" id="m_book_now">Book Now</a>
			   	<br><br>   			
			</div>
	  </div>   	
  </div>
<div class="container hide-on-med-and-down" >
   	 <h5 class="center sch light-blue-text">Schedule Up to Date</h5><br>
      <table class="centered highlight bordered">
        <thead>
          <tr>
              <th>Date</th>
              <th>Destination</th>         
              <th>Total Seats</th>
              <th>Customer</th>
              <th>Staff</th>
              <th>Student</th>
              <th>Remaining</th>
              <th>Passenger</th>
          </tr>
        </thead>
        <tbody id="getSchedule"></tbody>
      </table>
      <br><br>
    </div>
<div class="container">
    <!-- User Detial Modal Structure -->
	  <div id="user_detail" class="modal modal-fixed-footer">
		  <div id="modal_body" class="modal-content"> 
		  </div>
		  <div class="modal-footer">
		   <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Back</a>
		</div>
	</div>
</div>


<footer class="page-footer light-blue lighten-1 hide-on-med-and-down">
    <div class="container">
      <div class="row">
        <div class="col l6">
          <h5 class="white-text">Company Bio</h5>
          <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>
        </div>
        <div class="col l3 ">
        </div>
        <div class="col l3">
          <h5 class="white-text ">Connect</h5>
          <ul>
            <li><a class="white-text" href="#!">Link 1</a></li>
            <li><a class="white-text" href="#!">Link 2</a></li>
            <li><a class="white-text" href="#!">Link 3</a></li>
            <li><a class="white-text" href="#!">Link 4</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">vKirirom Pine View Resort</a>
      </div>
    </div>
  </footer>


  <!--  Scripts  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://unpkg.com/flatpickr"></script>
  <spring:url value="/resources/js/student.js" var="studentjs" />
  <script src ="${studentjs}" type="text/javascript"></script>
  </body>
</html>