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
<div class="navbar-fixed">
    <nav>
		<div class="container">
			<div class="nav-wrapper">
			<div class="row">
				<div class="col s12 m6">
					 <a href="#" class="brand-logo">Logo</a>
				</div>		
				<div class="col s0 l6">
					<ul id="nav-mobile" class="right hide-on-med-and-down">
			        <li class="avatar">
				        <a id="getProfile"  href="#">
				      		<img src="https://s-media-cache-ak0.pinimg.com/736x/64/fb/c9/64fbc98e98bebd0c06dc5f9345724658.jpg" alt="" class="circle profile">
				   		</a>
			   		 </li>      
			      </ul>
				</div>
			</div>
	    </div>
		</div> 
  </nav>
  </div>
<br>
<div class="container">
		<div class="row">
			<div class="col s12">
				<h5 class="title">Shuttle Bus Information</h5>
				<br>
			</div>
			<div class="col s12">
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
$(document).ready(function(){
$.ajax({
		type : "GET",
		async:false,
		cache:false,
		contentType : "application/json",
		url : "driverSchedule",
		timeout : 100000,
		success : function(data) {
			    var scheduleForm="";
				for(i=0;i<data.length;i++){	
   					scheduleForm+='<tr><td>'+data[i].date_of_travel
				   				+'</td><td>'+data[i].bus_model
				   				+'</td><td>'+data[i].driver_name
   								+'</td><td>'+data[i].destination_name
   								+'</td><td>'+data[i].total_available_seats
   								+'</td><td>'+data[i].customer_seats
   								+'</td><td>'+data[i].staff_seats
   								+'</td><td>'+data[i].student_seats
   								+'</td><td>'+data[i].est_departure_time+" to "+data[i].est_arrival_time
   								+'</td><td><a class="userDetail" value="'+data[i].bus_per_schedule_id
   						        +'" style="color:#ff9800;cursor:pointer">detail</a></td>';
   					//Get today
   					var currentTime = new Date();
					var month = currentTime.getMonth() + 1;
					var day = currentTime.getDate();
					var year = currentTime.getFullYear();
					if(day<10) {
						day = '0'+day
					} 
					if(month<10) {
						month = '0'+month
					} 
					var today = day + "/" + month + "/" + year;
   					if(data[i].date_of_travel==today){
   						var check = checkConfirm(data[i].bus_per_schedule_id);
   						console.log(check);
   						if(check.actual_departure_time==""||check.actual_departure_time==null){
   							scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 leaveConfirm" value="'+data[i].bus_per_schedule_id+'">Leave</a></td></tr>';
   						}else if(check.actual_arrival_time==""||check.actual_arrival_time==null){
   							scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 arriveConfirm" value="'+data[i].bus_per_schedule_id+'">Arrive</a></td></tr>';
   						}else{//if already done both
   							scheduleForm+='</td><td><a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a></td></tr>';
   						}		
   					}else{
   						scheduleForm+='</td><td><a class="btn red lighten-4" disabled>Leave</a></td></tr>';
   					}	        	    	
			}
			document.getElementById('getSchedule').innerHTML=scheduleForm;
		},
		error : function(e) {
			console.log("ERROR: ", e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
function checkConfirm(bus_id) {
	var confirm="";
	console.log("check confirm  "+bus_id);
	$.ajax({
			type : "POST",
  			async:false,
			cache:false,
				contentType :"application/json",
				data :bus_id,
				url : "checkConfirm",
				timeout : 100000,
				success : function(data) {
					confirm=data;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
    return confirm;
}
	
//Confirm Leave 
$('.leaveConfirm').click(function() {
		    var bus_id=$(this).attr("value") ;
		    console.log("leave "+bus_id);
   	   		$.ajax({
   	   				type : "POST",
	   	   			async:false,
	   				cache:false,
   	   				contentType : "application/json",
   	   				data :bus_id,
   	   				url : "leaveConfirm",
   	   				timeout : 100000,
   	   				success : function(bus_set) {
   	   					var arriveForm='<a class="btn light-blue lighten-1 arriveConfirm" value="'+bus_set+'">Arrive</a>';
   	   					document.getElementById(bus_set).innerHTML = arriveForm;
   	   				},
   	   				error : function(e) {
   	   					console.log("ERROR: ", e);
   	   				},
   	   				done : function(e) {
   	   					console.log("DONE");
   	   				}
   	   			});
   	   	$('.arriveConfirm').click(function() {
   		 var bus_id=$(this).attr("value") ;
   		    console.log("arrive "+bus_id);
   		 	arriveConfirm(bus_id);
   		    });
   	   		
});

$('.arriveConfirm').click(function() {
		 var bus_id=$(this).attr("value") ;
		  console.log("arrive "+bus_id);
		  arriveConfirm(bus_id);
	  		
});
function arriveConfirm(bus_per_id){
	$.ajax({
			type : "POST",
  			async:false,
			cache:false,
				contentType : "application/json",
				data :bus_per_id,
				url : "arriveConfirm",
				timeout : 100000,
				success : function(bus_set) {
					var arriveForm='<a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a>';
					document.getElementById(bus_set).innerHTML = arriveForm;
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
	});
}

	    

//Passenger Detail
$('.userDetail').click(function() {
   		    var bus_id=$(this).attr("value") ;
	   	    console.log(bus_id);
	   	   		$.ajax({
	   	   				type : "POST",
		   	   			async:false,
		   				cache:false,
	   	   				contentType : "application/json",
	   	   				data :bus_id,
	   	   				url : "driverGetDetail",
	   	   				timeout : 100000,
	   	   				success : function(data) {
	   	   					var passenger_modal="";
	   	  					for(i=0;i<data.length;i++){
	   			   					passenger_modal+='<tr><td>'+(i+1)
	   			   								+'</td><td>'+data[i].driver_name
	   			   								+'</td><td>'+data[i].passenger_name
	   			   								+'</td><td>'+data[i].batch
	   			   								+'</td><td>'+data[i].role
	   			   								+'</td><td>'+data[i].seat_number
	   			   								+'</td></tr>';	
	   	  						}
	   	  						document.getElementById('getDetail').innerHTML=passenger_modal;
								$('#user_detail').modal('open');
	   	   					          
	   	   					},
	   	   					error : function(e) {
	   	   						console.log("ERROR: ", e);
	   	   					},
	   	   					done : function(e) {
	   	   						console.log("DONE");
	   	   					}
	   	   				});
	   	   		 	

   	    });
}); 	 

</script>
</body>
</html>