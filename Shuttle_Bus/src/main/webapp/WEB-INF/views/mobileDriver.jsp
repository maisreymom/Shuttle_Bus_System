<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Mobile Driver</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link rel="stylesheet" href="https://unpkg.com/flatpickr/dist/flatpickr.min.css">
  
  
  <style type="text/css">
  
	.container {
	    width: 80%;
	}
  	.input-field{
  		height: 39px;
  		margin-top: -3px;
  		margin-bottom: -3px;
  	}
  	.bookingBtn{
  		width: 100%;
  		height: 100%;
  	}
  	.row_mobileBook {
	    margin-left: 5px;
	    margin-right: 5px;
	}
  	.mobile_schedule{
  		width: 100%;
  	}
	.bordered th, .bordered td {
   		 border: 1px solid gray;
	}
	.m_header{
		    margin-bottom: -6px !important;
			padding-bottom: 20px;
		    box-shadow: 0 2px 2px 0 rgba(0,0,0,0.14), 0 1px 5px 0 rgba(0,0,0,0.12), 0 3px 1px -2px rgba(0,0,0,0.2);
	}
</style>
  
</head>
<body>
 <nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="mobileDriver" class="brand-logo">Logo</a>
	  <ul id="nav-mobile" class="side-nav">
	  		<li><div class="divider"></div></li>
		    <li><a href="#!"><i class="material-icons">account_circle</i>Name: Mai Mom</a></li>
		    <li><div class="divider"></div></li>
	  </ul>
      <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
  </nav>
<div class="hide-on-large-only">
	<div class="row m_header">
		<br>
		<div><h6 class="header center orange-text"><b>Schedule Shuttle Bus Up to Date</b></h6></div>
	</div>
	<ul id="sch" class="collapsible" data-collapsible="accordion">
	</ul>
      
</div>


  <!--  Scripts  -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/js/materialize.min.js"></script> 
  <script src="https://unpkg.com/flatpickr"></script>

<script type="text/javascript">	

	$(document).ready(function() {
		$(".button-collapse").sideNav();
		$('.collapsible').collapsible();
		$.ajax({
			type : "GET",
			async:false,
			cache:false,
			contentType : "application/json",
			url : "mobileSchedule_Service",
			timeout : 100000,
			success : function(data) {
				    var scheduleForm="";
					for(i=0;i<data.length;i++){	
						scheduleForm+='<li><div class="collapsible-header';
						if(i==0){
							scheduleForm+=' active';
						}
						scheduleForm+='"><span><i class="material-icons">event</i>'+data[i].date_of_travel
									+'</span><span class="right"><i class="material-icons">place</i>'+data[i].destination_name
									+'</span></div><div class="collapsible-body">'
									+'<table class="centered">';			
	
									
				   		scheduleForm+='<tr><th>Bus Model : </th><td>'
							        +data[i].bus_model+'</td></tr>'
							        +'<tr><th>Driver Name : </th><td>'
							        +data[i].driver_name+'</td></tr>'  
							        +'<tr><th>Total Seats :</th><td>'
							        +data[i].total_available_seats+'</td></tr>'
							        +'<tr><th>Customer Seats :</th><td>'
							        +data[i].customer_seats+'</td></tr>'
							        +'<tr><th>Staff Seats :</th><td>'
							        +data[i].staff_seats+'</td></tr>'
							        +'<tr><th>Student Seat :</th><td>'
							        +data[i].student_seats+'</td></tr>'
							        +'<tr><th>Travel Time :</th><td>'
							        +data[i].est_departure_time+" to "+data[i].est_arrival_time+'</td></tr>'
							      	+'<tr>';
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
					   			scheduleForm+='<tr><th>Driver Confirm : </th><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 leaveConfirm" value="'+data[i].bus_per_schedule_id+'">Leave</a></td></tr>';
					   		}else if(check.actual_arrival_time==""||check.actual_arrival_time==null){
					   			scheduleForm+='<tr><th>Driver Confirm : </th><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 arriveConfirm" value="'+data[i].bus_per_schedule_id+'">Arrive</a></td></tr>';
					   			//scheduleForm+='</td><td id="'+data[i].bus_per_schedule_id+'"><a class="btn light-blue lighten-1 arriveConfirm" value="'+data[i].bus_per_schedule_id+'">Arrive</a></td></tr>';
					   		}else{//if already done both
					   			scheduleForm+='<tr><th>Driver Confirm : </th><td><a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a></td></tr>';
					   			//scheduleForm+='</td><td><a class="btn red lighten-4 driverConfirm"  disabled>Arrived</a></td></tr>';
					   		}		
				   		}else{
				   			scheduleForm+='<tr><th>Driver Confirm : </th><td><a class="btn red lighten-4 driverConfirm"  disabled>Leave</a></td></tr>';
				   		}
				   		scheduleForm+='<th class="center" colspan="2">Passenger List</th></tr></table><table class="centered bordered"><thead><tr><th>No.</th><th>Name</th><th>Role</th></tr></thead>';
					    console.log("ff "+data[i].bus_per_schedule_id);
						var pass=passenDetail(data[i].bus_per_schedule_id);
						console.log(pass);
						for(j=0;j<pass.length;j++){
							scheduleForm+='<tr><td>'+(j+1)
		   								+'</td><td>'+pass[j].passenger_name
		   								+'</td><td>'+pass[j].role
		   								+'</td></tr>';	
  						}
						scheduleForm+='</table></div></li>';    	
					}
					console.log(scheduleForm);
					document.getElementById('sch').innerHTML=scheduleForm;
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		function passenDetail(bus_id){
			console.log(bus_id);
			var passen;
			$.ajax({
	   				type : "POST",
   	   			async:false,
   				cache:false,
	   				contentType : "application/json",
	   				data :bus_id,
	   				url : "driverGetDetail",
	   				timeout : 100000,
	   				success : function(data) {
	   					passen=data;
	   					},
	   					error : function(e) {
	   						console.log("ERROR: ", e);
	   					},
	   					done : function(e) {
	   						console.log("DONE");
	   					}
	   				});
			return passen;
		}
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


	});

 </script>
  </body>
</html>